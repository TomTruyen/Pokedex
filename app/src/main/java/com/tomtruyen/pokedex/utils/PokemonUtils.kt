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

        fun getDetailBackgroundGradient(type: String): List<Color> {
            return when (type.lowercase()) {
                "grass" -> listOf(Color(0xFF7ECD8B), Color(0xFF89E2B3))
                "bug" -> listOf(Color(0xFFB5C91C), Color(R.color.bug))
                "fire" -> listOf(Color(0xFFE68061), Color(0xFFDBB469))
                "water" -> listOf(Color(0xFF4DA4DB), Color(R.color.water))
                "fighting" -> listOf(Color(0xFFDB352E), Color(R.color.fighting))
                "normal" -> listOf(Color(R.color.normal), Color(0xFFBEc9CC))
                "electric" -> listOf(Color(0xFFf7dc2c), Color(R.color.electric))
                "psychic" -> listOf(Color(0xFFfa6e99), Color(R.color.psychic))
                "poison" -> listOf(Color(0xFFd38fe3), Color(R.color.poison))
                "ghost" -> listOf(Color(0xFF8c70ba), Color(R.color.ghost))
                "rock" ->listOf(Color(0xFFd1b93d), Color(R.color.rock))
                "ground" -> listOf(Color(0xFFf5ce6c), Color(R.color.ground))
                "dark" -> listOf(Color(0xFF8a6b57), Color(R.color.dark))
                "ice" -> listOf(Color(0xFFa4edea), Color(R.color.ice))
                "flying" -> listOf(Color(0xFFb59ff5), Color(R.color.flying))
                "dragon" -> listOf(Color(0xFF8150fa), Color(R.color.dragon))
                "fairy" -> listOf(Color(0xFFf097c3), Color(R.color.fairy))
                "steel" -> listOf(Color(0xFFcfcfe8), Color(R.color.steel))
                else -> return listOf(Color(0xFFE5E5E5), Color.White)
            }
        }
    }
}