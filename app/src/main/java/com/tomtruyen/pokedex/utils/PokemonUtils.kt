package com.tomtruyen.pokedex.utils

import com.tomtruyen.pokedex.R

class PokemonUtils {
    companion object {
        fun getTypeColor(type: String): Int {
            return when (type.lowercase()) {
                "grass" -> R.color.grass
                "bug" -> R.color.bug
                "fire" -> R.color.fire
                "water" -> R.color.water
                "fighting" -> R.color.fighting
                "normal" -> R.color.normal
                "electric" -> R.color.electric
                "psychic" -> R.color.psychic
                "poison" -> R.color.poison
                "ghost" -> R.color.ghost
                "rock" -> R.color.rock
                "ground" -> R.color.ground
                "dark" -> R.color.dark
                "ice" -> R.color.ice
                "flying" -> R.color.flying
                "dragon" -> R.color.dragon
                "fairy" -> R.color.fairy
                "steel" -> R.color.steel
                else -> return R.color.black
            }
        }
    }
}