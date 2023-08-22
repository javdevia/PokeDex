package com.project.pokedex

import com.google.gson.annotations.SerializedName

data class PokemonListDataResponse(
    @SerializedName("results") val results: List<PokemonDataResponse>
)

data class PokemonDataResponse(
    @SerializedName("name") val pokemonName: String,
    @SerializedName("url") val pokemonUrl: String
)