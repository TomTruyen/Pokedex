package com.tomtruyen.pokedex.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.models.BasePokemon
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.ui.shared.components.CardText
import com.tomtruyen.pokedex.ui.shared.components.CardTitle
import com.tomtruyen.pokedex.ui.shared.components.PokedexItem
import com.tomtruyen.pokedex.ui.shared.components.PokemonTypeChip
import com.tomtruyen.pokedex.utils.PokemonUtils

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