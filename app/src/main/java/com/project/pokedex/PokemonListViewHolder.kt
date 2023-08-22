package com.project.pokedex

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.project.pokedex.databinding.ItemPokemonBinding

class PokemonListViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemonDataResponse: PokemonDataResponse) {
        binding.tvPokemonName.text =pokemonDataResponse.pokemonName
    }
}