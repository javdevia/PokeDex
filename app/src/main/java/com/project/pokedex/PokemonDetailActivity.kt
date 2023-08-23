package com.project.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.project.pokedex.ListActivity.Companion.EXTRA_NAME
import com.project.pokedex.databinding.ActivityPokemonDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailBinding
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit2()
        val pokemonName = intent.getStringExtra(EXTRA_NAME).orEmpty()
        searchDetail(pokemonName)
    }

    private fun getRetrofit2(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchDetail(name:String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val myResponse =
                retrofit.create(ApiService::class.java).searchPokemonDetail(name)
            val response = myResponse.body()

            if (response != null) {
                runOnUiThread {
                    binding.tvPokemonName.text = response.pokemonName
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

}



