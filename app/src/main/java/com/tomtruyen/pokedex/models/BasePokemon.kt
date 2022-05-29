package com.tomtruyen.pokedex.models

interface BasePokemon {
    val id: Int
    val name: String
    val sprites: PokemonSprites
    val types: List<PokemonType>
}