package com.tomtruyen.pokedex.service

import com.tomtruyen.pokedex.network.PokemonInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://stoplight.io/mocks/appwise-be/pokemon/57519009/")
        .build()

object PokemonApi {
    val service: PokemonInterface by lazy { retrofit.create(PokemonInterface::class.java) }
}
