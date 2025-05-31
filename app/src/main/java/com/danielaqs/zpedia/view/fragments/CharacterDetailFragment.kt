package com.danielaqs.zpedia.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.danielaqs.zpedia.data.remote.CharacterApi
import com.danielaqs.zpedia.databinding.FragmentCharacterDetailBinding
import com.danielaqs.zpedia.utils.Constants
import com.danielaqs.zpedia.R
import com.danielaqs.zpedia.view.TransformationsAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_ID = Constants.ID


class GameDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        //Instanciamos nuestra interfaz a nuestra Api

        val characterApi = retrofit.create(CharacterApi::class.java)

        lifecycleScope.launch {
            if (id == null) {
                Toast.makeText(requireContext(), R.string.invalid_id, Toast.LENGTH_SHORT).show()
                return@launch
            }
            try{

                val character = characterApi.getCharacterDetail(id!!)

                binding.ChDetailName.text = character.name
                binding.tvLongDesc.text = character.description
                binding.tvKi.text = character.ki
                binding.tvMaxKi.text = character.maxKi
                binding.tvRace.text = character.race
                binding.tvGender.text = character.gender
                binding.tvAffiliation.text = character.affiliation


                if (character.transformations.isEmpty()) {
                    binding.tvTransformaciones.text = getString(R.string.sin_transformaciones)
                    binding.rvTransformations.visibility = View.GONE
                } else {
                    binding.rvTransformations.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvTransformations.adapter = TransformationsAdapter(character.transformations) { selectedTransformation ->
                        Toast.makeText(
                            requireContext(),
                            selectedTransformation.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                Picasso.get()
                    .load(character.image)
                    .into(binding.ivImage)

                //Picasso.get().load(character.image).into(binding.ivImage)

                val rotation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_horizontal)
                binding.ivImage.startAnimation(rotation)

                Picasso.get()
                    .load(character.image)
                    .into(binding.backgroundImage)

            }catch (e: Exception){
                Log.d(Constants.LOGTAG, e.toString())
            }finally {
                binding.pbLoading.visibility = View.INVISIBLE
            }


        }

    }

    override fun onDestroy() {
        binding.ivImage.clearAnimation()
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }
}
