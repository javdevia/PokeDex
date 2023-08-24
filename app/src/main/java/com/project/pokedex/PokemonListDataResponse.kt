package com.project.pokedex

import com.google.gson.annotations.SerializedName

data class PokemonListDataResponse(
    @SerializedName("results") val results: List<PokemonDataResponse>
)

data class PokemonDataResponse(
    @SerializedName("name") val pokemonName: String,
    @SerializedName("url") val pokemonUrl: String
)

data class PokemonDetailResponse(
    @SerializedName ("sprites") val pokemonSprites: PokemonImageResponse,
    @SerializedName ("name") val pokemonName: String,
    @SerializedName ("stats") val pokemonStats: List<PokemonStatsResponse>
)

data class PokemonImageResponse (
    @SerializedName ("front_default") val pokemonImageUrl: String
)

data class PokemonStatsResponse (
    @SerializedName ("base_stat") val pokemonStatValue: Int
        )