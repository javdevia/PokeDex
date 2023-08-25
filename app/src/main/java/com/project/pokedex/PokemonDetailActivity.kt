package com.project.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import coil.load
import com.project.pokedex.API.ApiServiceManager
import com.project.pokedex.ListActivity.Companion.EXTRA_NAME
import com.project.pokedex.databinding.ActivityPokemonDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


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
                    binding.tvPokemonId.text = "ID: ${response.pokemonId}"
                    binding.tvWeight.text = "Weight: ${response.pokemonWeight}"
                    binding.tvHeight.text = "Height: ${response.pokemonHeight}"
                    binding.progressBar.isVisible = false


                    updateStats(response)


                }

            }
        }
    }

    private fun updateStats(response: PokemonDetailResponse) {

        // Inicializando Stats como una lista de Int con los valores de los Stats del Pokemon
        val stats = listOf(
            response.pokemonStats[0].pokemonStatValue.toFloat(),
            response.pokemonStats[1].pokemonStatValue.toFloat(),
            response.pokemonStats[2].pokemonStatValue.toFloat(),
            response.pokemonStats[3].pokemonStatValue.toFloat(),
            response.pokemonStats[4].pokemonStatValue.toFloat(),
            response.pokemonStats[5].pokemonStatValue.toFloat()
        )

        // Actualización del valor del stat en el TextView que muestra el valor
        binding.tvValueHp.text = stats[0].roundToInt().toString()
        binding.tvValueAttack.text = stats[1].roundToInt().toString()
        binding.tvValueDefense.text = stats[2].roundToInt().toString()
        binding.tvValueSpecialAttack.text = stats[3].roundToInt().toString()
        binding.tvValueSpecialDefense.text = stats[4].roundToInt().toString()
        binding.tvValueSpeed.text = stats[5].roundToInt().toString()

        // Actualización de la altura de la gráfica (min 0dp, max 148dp)
        updateHeight(binding.cvHp, stats[0])
        updateHeight(binding.cvAttack, stats[1])
        updateHeight(binding.cvDefense, stats[2])
        updateHeight(binding.cvSpecialAttack, stats[3])
        updateHeight(binding.cvSpecialDefense, stats[4])
        updateHeight(binding.cvSpeed, stats[5])
    }

    private fun updateHeight(view: CardView, stat: Float) {
        val params = view.layoutParams
        val height = stat * 148 / 255
        params.height = pxToDp(height)
        view.layoutParams = params

    }

    private fun pxToDp(px: Float) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()

}





