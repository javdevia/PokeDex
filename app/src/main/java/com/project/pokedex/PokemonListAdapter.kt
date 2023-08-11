package com.project.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonListAdapter(
    var pokemonList: List<PokemonDataResponse> = emptyList()) :
    RecyclerView.Adapter<PokemonListViewHolder>(){

    fun updateList(list:List<PokemonDataResponse>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.render(pokemonList[position])
    }

}