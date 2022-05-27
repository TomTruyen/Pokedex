package com.tomtruyen.pokedex.models

import androidx.annotation.NonNull
import androidx.room.PrimaryKey

interface BasePokemon {
    val id: Int;
    val name: String;
    val sprites: Map<String, Any>;
    val types: List<PokemonType>;
}