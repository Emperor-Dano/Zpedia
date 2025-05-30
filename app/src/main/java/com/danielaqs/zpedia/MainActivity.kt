package com.danielaqs.zpedia

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielaqs.zpedia.data.remote.CharacterApi
import com.danielaqs.zpedia.databinding.ActivityMainBinding
import com.danielaqs.zpedia.data.remote.model.Character
import com.danielaqs.zpedia.utils.Constants
import com.danielaqs.zpedia.view.fragments.CharacterListFragment
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        /*val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val charApi = retrofit.create(CharacterApi::class.java)

        lifecycleScope.launch{

            try {
                val characters = charApi.getCharacters()
                //val characters = charApi.getCharacterDetail(1)
                Log.d(Constants.LOGTAG, "Respuesta: $characters")
            }catch (e: Exception){
                Log.d(Constants.LOGTAG, "No se pudo")
            }
        }


        val characters = mutableListOf<Character>()

        for(i in 1 .. 100){
            val characterTmp = Character(i, "Libro $i", "Editorial $i")
            characters.add(characterTmp)
        }

        //Instanciamos el adapter con el listado de libros
        val charactersAdapter = CharactersAdapter(characters){ selectedCharacter ->

            Toast.makeText(
                this,
                "Click en el personaje: ${selectedCharacter.name}",
                Toast.LENGTH_SHORT
            ).show()

        }

        //Establecemos los elementos del recycler view
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvCharacters.adapter = charactersAdapter */

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CharacterListFragment())
            .commit()
    }
}