package com.project.pokedex

import com.google.gson.annotations.SerializedName

data class PokemonDataResponse(
    @SerializedName("name") val PokemonName: String,
    @SerializedName("id") val pokemonId: Int
)