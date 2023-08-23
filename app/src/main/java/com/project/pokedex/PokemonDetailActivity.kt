package com.project.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.project.pokedex.ListActivity.Companion.EXTRA_NAME
import com.project.pokedex.databinding.ActivityPokemonDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pokemonName = intent.getStringExtra(EXTRA_NAME).orEmpty()
        searchDetail(pokemonName)
    }


    private fun searchDetail(name: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ApiServiceManager.apiService
            val myResponse =
                apiService.searchPokemonDetail(name)
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



