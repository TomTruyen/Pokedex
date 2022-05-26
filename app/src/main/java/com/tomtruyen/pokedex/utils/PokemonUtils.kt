package com.tomtruyen.pokedex.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.PokemonStatistic

class PokemonUtils {
    companion object {
        fun getStatDisplayName(statistic: PokemonStatistic): String {
            val name = statistic.stat["name"] ?: return ""

            return when(name.lowercase()) {
                "hp" -> "HP"
                "attack" -> "Attack"
                "defense" -> "Defense"
                "special-attack" -> "Sp. Atk"
                "special-defense" -> "Sp. Def"
                "speed" -> "Speed"
                else -> "Total"
            }
        }


        fun calculateTotal(stats: List<PokemonStatistic>): Int {
            return stats.fold(0) { sum, element -> sum + element.baseStat }
        }

        @Suppress("UNCHECKED_CAST")
        fun getSpritesList(sprites: Map<String, Any>): List<Any?> {
            // DEPRECATED - Gets all sprites in a list
            // this isn't used anymore because some sprites are just really bad looking or too similar
            // There is also an image url for Bulbasaur which doesn't return an image at all
            //            val spritesList = mutableListOf<String>()
            //
            //            sprites.forEach { sprite ->
            //                if(sprite.value is String && (sprite.value as String).isNotEmpty()) {
            //                    spritesList.add(sprite.value as String)
            //                    return@forEach
            //                }
            //
            //                if(sprite.value is Map<*, *>) {
            //                    try {
            //                        spritesList.addAll(getSpritesList(sprite.value as Map<String, Any>))
            //                    } catch (e: Exception) {
            //                        // In case the sprite value cant be cast to a Map<String, Any>
            //                    }
            //                }
            //            }
            //
            //            return spritesList

            // Return the front, front shiny, back and back shiny sprites
            return listOf(
                sprites["front_default"],
                sprites["front_shiny"],
                sprites["back_default"],
                sprites["back_shiny"],
            )
        }

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