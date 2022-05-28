package com.tomtruyen.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionChain(
    val chain: Evolution
)

data class Evolution(
    @SerializedName("evolves_to")
    val evolution: List<Evolution>,
    val species: Species
)

data class Species(
    val name: String,
    val url: String
)