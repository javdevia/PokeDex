package com.project.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import coil.load
import com.project.pokedex.API.ApiServiceManager
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
                    binding.ivPokemonDetail.load(response.pokemonSprites.pokemonImageUrl)
                    binding.progressBar.isVisible = false

                    // Actualización del valor de los Stats en los TextView
                    binding.tvValueHp.text = response.pokemonStats[0].pokemonStatValue.toString()
                    binding.tvValueAttack.text = response.pokemonStats[1].pokemonStatValue.toString()
                    binding.tvValueDefense.text = response.pokemonStats[2].pokemonStatValue.toString()
                    binding.tvValueSpecialAttack.text = response.pokemonStats[3].pokemonStatValue.toString()
                    binding.tvValueSpecialDefense.text = response.pokemonStats[4].pokemonStatValue.toString()
                    binding.tvValueSpeed.text = response.pokemonStats[5].pokemonStatValue.toString()

                    // Altura de los gráficos de Stats
                    val heightHp: Double = response.pokemonStats[0].pokemonStatValue.toDouble()/255*248


                }

            }
        }
    }
}





