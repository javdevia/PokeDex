package com.project.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.pokedex.databinding.ActivityListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        searchRegion()

        // Inicialización de RecyclerView
        adapter = PokemonListAdapter()
        binding.rvPokemonList.setHasFixedSize(true)
        binding.rvPokemonList.layoutManager = LinearLayoutManager(this)
        binding.rvPokemonList.adapter = adapter
    }

    private fun searchRegion() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val myResponse =
                retrofit.create(ApiService::class.java).searchPokemonList(151)
            val response = myResponse.body()

            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.results)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}