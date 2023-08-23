package com.project.pokedex.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceManager {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)


}