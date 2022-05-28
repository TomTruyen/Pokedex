package com.tomtruyen.pokedex.network

import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.models.PokemonEvolutionChain
import com.tomtruyen.pokedex.models.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonInterface {
    @GET("https://stoplight.io/mocks/appwise-be/pokemon/57519009/pokemon")
    suspend fun getAll(): List<Pokemon>

    @GET("pokemon/{id}")
    suspend fun getById(@Path("id") id: Int): PokemonDetails

    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") id: Int): PokemonSpecies

    @GET
    suspend fun getEvolutionChain(@Url url: String): PokemonEvolutionChain
}