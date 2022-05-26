package com.tomtruyen.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonStatistic(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: Map<String, String>
)