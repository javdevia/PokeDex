package com.project.pokedex.API

import com.project.pokedex.PokemonDataResponse
import com.project.pokedex.PokemonDetailResponse
import com.project.pokedex.PokemonListDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun searchPokemonList(@Query("limit") limit: Int): Response <PokemonListDataResponse>

    @GET("pokemon/{name}")
    suspend fun searchPokemonDetail(@Path("name") pokemonName: String): Response<PokemonDetailResponse>
}