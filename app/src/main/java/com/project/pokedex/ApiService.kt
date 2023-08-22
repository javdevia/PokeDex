package com.project.pokedex

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun searchPokemonList(@Query("limit") limit: Int): Response <PokemonListDataResponse>

    // @GET("pokemon/{name}")
    // suspend fun searchPokemon(@Path("name") pokemonName: String): Response<PokemonDataResponse>
}