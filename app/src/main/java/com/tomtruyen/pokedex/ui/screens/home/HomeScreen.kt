package com.tomtruyen.pokedex.ui.screens.home
import com.tomtruyen.pokedex.ui.shared.components.Error

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tomtruyen.pokedex.ui.shared.components.PokedexItem
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.tomtruyen.pokedex.ui.shared.components.Error

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {
    val pokemon by remember { viewModel.pokemon }
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    if(isLoading) {
        CircularProgressIndicator(
            color = Color.Red
        )
    } else if (error.isNotEmpty()) {
        Error(error = error)
    } else {
        LazyColumn {
            item {
                Text(text = pokemon.size.toString())
            }
            item {
                Text(text = error)
            }
            item {
                PokedexItem()
            }
        }
    }
}