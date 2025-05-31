package com.danielaqs.zpedia.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielaqs.zpedia.R
import com.danielaqs.zpedia.data.remote.CharacterApi
import com.danielaqs.zpedia.databinding.FragmentCharacterListBinding
import com.danielaqs.zpedia.utils.Constants
import com.danielaqs.zpedia.view.CharactersAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharacterListFragment : Fragment() {


    private var _binding : FragmentCharacterListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
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


        val gamesApi = retrofit.create(CharacterApi::class.java)


        lifecycleScope.launch {


            try{
                //val games = gamesApi.getGames()
                val characters = gamesApi.getCharacters()
                //Log.d(Constants.LOGTAG, "Respuesta: $characters")


                //binding.rvGames.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvCharacters.adapter = CharactersAdapter(characters.items){ selectedCharacter ->


                    Toast.makeText(
                        requireContext(),
                        "${Constants.seleccionaste} ${selectedCharacter.name}",
                        Toast.LENGTH_SHORT
                    ).show()


                    //Manejo del click
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GameDetailFragment.newInstance(
                            selectedCharacter.id
                        ))
                        .addToBackStack(null)
                        .commit()
                }
                Picasso.get()
                    .load(R.drawable.main_background_2)
                    .into(binding.backgroundImage)

            }
            catch (e: Exception){
                //Manejamos el error de conexión
            }
            finally {
                binding.pbLoading.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
