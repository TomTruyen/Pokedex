package com.tomtruyen.pokedex.models

data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: Map<String, String>,
    val types: List<PokemonType>,
)