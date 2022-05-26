package com.tomtruyen.pokedex.models

data class PokemonDetails(
    val id: Int,
    val name: String,
    val abilities: List<PokemonAbility>,
    val baseExperience: Int,
    val sprites: Map<String, Any>,
    val types: List<PokemonType>,
    val stats: List<PokemonStatistic>,
    val height: Int,
    val weight: Int
)
