package com.tomtruyen.pokedex.network

import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {
    @GET("https://stoplight.io/mocks/appwise-be/pokemon/57519009/pokemon")
    suspend fun getAll(): List<Pokemon>

    @GET("{id}")
    suspend fun getById(@Path("id") id: Int): PokemonDetails
}