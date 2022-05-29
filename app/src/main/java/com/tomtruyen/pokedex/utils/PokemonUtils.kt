package com.tomtruyen.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.database.repository.PokemonRepository
import com.tomtruyen.pokedex.models.*
import kotlinx.coroutines.coroutineScope

class PokemonUtils {
    companion object {
        /**
         * @param PokemonStatistic
         *
         * @return String
         */
        fun getStatDisplayName(statistic: PokemonStatistic): String {
            val name = statistic.stat["name"] ?: return ""

            return when (name.lowercase()) {
                "hp" -> "HP"
                "attack" -> "Attack"
                "defense" -> "Defense"
                "special-attack" -> "Sp. Atk"
                "special-defense" -> "Sp. Def"
                "speed" -> "Speed"
                else -> "Total"
            }
        }

        /**
         * @param List<PokemonMove>
         *
         * @return List<PokemonMove>
         */
        fun getLevelUpMoves(moves: List<PokemonMove>): List<PokemonMove> {
            return moves.filter {
                it.versionGroupDetails.isNotEmpty() && it.versionGroupDetails[0].moveLearnMethod.name == "level-up"
            }.sortedBy { it.versionGroupDetails[0].levelLearnedAt }
        }

        /**
         * @param List<PokemonStatistic>
         *
         * @return Int
         */
        fun calculateTotal(stats: List<PokemonStatistic>): Int {
            return stats.fold(0) { sum, element -> sum + element.baseStat }
        }

        /**
         * @param Map<String, Any>
         *
         * @return List<Any?>
         */
        fun getSpritesList(sprites: PokemonSprites): List<Any?> {
            return listOf(
                sprites.front,
                sprites.frontShiny,
                sprites.back,
                sprites.backShiny,
            )
        }

        /**
         * @param String
         *
         * @return Int
         */
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
                else -> R.color.black
            }
        }

        /**
         * @param String
         *
         * @return List<Color>
         */
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
                "rock" -> listOf(Color(0xFFd1b93d), Color(R.color.rock))
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

        /*
        * This could also be achieved by looping over all pokemon and getting their types
        * but because these have been declared for colors we will just declare them here
        * alternatively these could also be declared as string-array in arrays.xml inside values dir
        *
        * @return List<String>
        * */
        fun getAllTypes(): List<String> {
            return listOf(
                "grass",
                "bug",
                "fire",
                "water",
                "fighting",
                "normal",
                "electric",
                "psychic",
                "poison",
                "ghost",
                "rock",
                "ground",
                "dark",
                "ice",
                "flying",
                "dragon",
                "fairy",
                "steel",
            )
        }

        suspend fun getListOfEvolutions(
            pokemonRepository: PokemonRepository,
            evolutions: Evolution
        ): List<Pokemon> {
            val evolutionList = mutableListOf<Pokemon>()

            coroutineScope {
                val pokemon = pokemonRepository.getByName(evolutions.species.name)

                if (pokemon != null) {
                    evolutionList.add(pokemon)
                }

                for (evolution in evolutions.evolution) {
                    evolutionList.addAll(getListOfEvolutions(pokemonRepository, evolution))
                }
            }

            return evolutionList
        }

        fun getTypeIcon(type: String): Int? {
            return when (type.lowercase()) {
                "grass" -> R.drawable.ic_grass_type
                "bug" -> R.drawable.ic_bug_type
                "fire" -> R.drawable.ic_fire_type
                "water" -> R.drawable.ic_water_type
                "fighting" -> R.drawable.ic_fighting_type
                "normal" -> R.drawable.ic_normal_type
                "electric" -> R.drawable.ic_electric_type
                "psychic" -> R.drawable.ic_psychic_type
                "poison" -> R.drawable.ic_poison_type
                "ghost" -> R.drawable.ic_ghost_type
                "rock" -> R.drawable.ic_rock_type
                "ground" -> R.drawable.ic_ground_type
                "dark" -> R.drawable.ic_dark_type
                "ice" -> R.drawable.ic_ice_type
                "flying" -> R.drawable.ic_flying_type
                "dragon" -> R.drawable.ic_dragon_type
                "fairy" -> R.drawable.ic_fairy_type
                "steel" -> R.drawable.ic_steel_type
                else -> null
            }
        }
    }
}