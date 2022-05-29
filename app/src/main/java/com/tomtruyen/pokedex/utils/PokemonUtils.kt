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
    }
}
