package com.tomtruyen.pokedex.network

import com.tomtruyen.pokedex.models.Pokemon
import retrofit2.http.GET

interface PokemonInterface {
    @GET("pokemon")
    suspend fun getAll(): List<Pokemon>
}