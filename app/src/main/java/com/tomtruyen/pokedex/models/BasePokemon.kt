package com.tomtruyen.pokedex.models

interface BasePokemon {
    val id: Int
    val name: String
    val sprites: Map<String, Any>
    val types: List<PokemonType>
}