package com.tomtruyen.pokedex.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.ui.shared.components.CardTitle
import com.tomtruyen.pokedex.ui.shared.components.PokedexItem

@Composable
fun Evolutions(navController: NavHostController, pokemon: PokemonDetails) {
    Column {
        CardTitle(text = "Evolutie")
        pokemon.evolutions?.let { evolutions ->
            evolutions.forEach { evolution ->
                val active = pokemon.id == evolution.id

                PokedexItem(
                    pokemon = evolution,
                    navController = navController,
                    color = Color.White.copy(alpha = if (active) 1f else 0.5f),
                    elevation = 0.dp
                )
            }
        }
    }
}