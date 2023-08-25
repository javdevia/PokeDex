package com.project.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.pokedex.API.ApiServiceManager
import com.project.pokedex.databinding.ActivityListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        searchRegion()

        // Inicialización de RecyclerView
        adapter = PokemonListAdapter { navigateToDetail(it) }
        binding.rvPokemonList.setHasFixedSize(true)
        binding.rvPokemonList.layoutManager = GridLayoutManager(this,3)
        binding.rvPokemonList.adapter = adapter

    }

    private fun searchRegion() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ApiServiceManager.apiService
            val myResponse =
                apiService.searchPokemonList(151)
            val response = myResponse.body()

            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.results)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun navigateToDetail(name: String) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(EXTRA_NAME, name)
        startActivity(intent)
    }
}