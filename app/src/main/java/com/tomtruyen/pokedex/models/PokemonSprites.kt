package com.tomtruyen.pokedex.models

import com.google.gson.annotations.SerializedName

class PokemonSprites(
    @SerializedName("front_default")
    val front: String,
    @SerializedName("back_default")
    val back: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("back_shiny")
    val backShiny: String
)