package com.tomtruyen.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("evolution_chain")
    val evolutionChain: Map<String, String>
)