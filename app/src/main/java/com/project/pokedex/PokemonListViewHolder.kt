package com.project.pokedex

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.project.pokedex.databinding.ItemPokemonBinding

class PokemonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemonDataResponse: PokemonDataResponse, onItemSelected: (String) -> Unit) {
        binding.tvPokemonName.text = pokemonDataResponse.pokemonName
        binding.root.setOnClickListener {
            onItemSelected(pokemonDataResponse.pokemonName)
        }
    }
}