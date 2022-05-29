package com.tomtruyen.pokedex.utils

import androidx.compose.ui.graphics.Color
import com.tomtruyen.pokedex.R

class PokemonTypeUtils(
    val type: String = "",
    val color: Int = -1,
    val background: List<Color> = listOf(),
    val icon: Int = -1,
) {
    companion object {
        fun types(): List<PokemonTypeUtils> {
            return listOf(
                PokemonTypeUtils(
                    type = "grass",
                    color = R.color.grass,
                    background = listOf(Color(0xFF7ECD8B), Color(0xFF89E2B3)),
                    icon = R.drawable.ic_grass_type
                ),
                PokemonTypeUtils(
                    type = "bug",
                    color = R.color.bug,
                    background = listOf(Color(0xFFB5C91C), Color(R.color.bug)),
                    icon = R.drawable.ic_bug_type
                ),
                PokemonTypeUtils(
                    type = "fire",
                    color = R.color.fire,
                    background = listOf(Color(0xFFE68061), Color(0xFFDBB469)),
                    icon = R.drawable.ic_fire_type
                ),
                PokemonTypeUtils(
                    type = "water",
                    color = R.color.water,
                    background = listOf(Color(0xFF4DA4DB), Color(R.color.water)),
                    icon = R.drawable.ic_water_type
                ),
                PokemonTypeUtils(
                    type = "fighting",
                    color = R.color.fighting,
                    background = listOf(Color(0xFFDB352E), Color(R.color.fighting)),
                    icon = R.drawable.ic_fighting_type
                ),
                PokemonTypeUtils(
                    type = "normal",
                    color = R.color.normal,
                    background = listOf(Color(R.color.normal), Color(0xFFBEc9CC)),
                    icon = R.drawable.ic_normal_type
                ),
                PokemonTypeUtils(
                    type = "electric",
                    color = R.color.electric,
                    background = listOf(Color(0xFFf7dc2c), Color(R.color.electric)),
                    icon = R.drawable.ic_electric_type
                ),
                PokemonTypeUtils(
                    type = "psychic",
                    color = R.color.psychic,
                    background = listOf(Color(0xFFfa6e99), Color(R.color.psychic)),
                    icon = R.drawable.ic_psychic_type
                ),
                PokemonTypeUtils(
                    type = "poison",
                    color = R.color.poison,
                    background = listOf(Color(0xFFd38fe3), Color(R.color.poison)),
                    icon = R.drawable.ic_poison_type
                ),
                PokemonTypeUtils(
                    type = "ghost",
                    color = R.color.ghost,
                    background = listOf(Color(0xFF8c70ba), Color(R.color.ghost)),
                    icon = R.drawable.ic_ghost_type
                ),
                PokemonTypeUtils(
                    type = "rock",
                    color = R.color.rock,
                    background = listOf(Color(0xFFd1b93d), Color(R.color.rock)),
                    icon = R.drawable.ic_rock_type
                ),
                PokemonTypeUtils(
                    type = "ground",
                    color = R.color.ground,
                    background = listOf(Color(0xFFf5ce6c), Color(R.color.ground)),
                    icon = R.drawable.ic_ground_type
                ),
                PokemonTypeUtils(
                    type = "dark",
                    color = R.color.dark,
                    background = listOf(Color(0xFF8a6b57), Color(R.color.dark)),
                    icon = R.drawable.ic_dark_type
                ),
                PokemonTypeUtils(
                    type = "ice",
                    color = R.color.ice,
                    background = listOf(Color(0xFFa4edea), Color(R.color.ice)),
                    icon = R.drawable.ic_ice_type
                ),
                PokemonTypeUtils(
                    type = "flying",
                    color = R.color.flying,
                    background = listOf(Color(0xFFb59ff5), Color(R.color.flying)),
                    icon = R.drawable.ic_flying_type
                ),
                PokemonTypeUtils(
                    type = "dragon",
                    color = R.color.dragon,
                    background = listOf(Color(0xFF8150fa), Color(R.color.dragon)),
                    icon = R.drawable.ic_dragon_type
                ),
                PokemonTypeUtils(
                    type = "fairy",
                    color = R.color.fairy,
                    background = listOf(Color(0xFFf097c3), Color(R.color.fairy)),
                    icon = R.drawable.ic_fairy_type
                ),
                PokemonTypeUtils(
                    type = "steel",
                    color = R.color.steel,
                    background = listOf(Color(0xFFcfcfe8), Color(R.color.steel)),
                    icon = R.drawable.ic_steel_type
                )
            )
        }

        fun find(type: String): PokemonTypeUtils {
            return types().find { it.type == type } ?: PokemonTypeUtils()
        }

        fun getAllTypes(): List<String> {
            return types().map { it.type }
        }
    }
}


