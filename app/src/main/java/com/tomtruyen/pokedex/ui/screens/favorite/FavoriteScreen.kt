package com.tomtruyen.pokedex.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.screens.detail.DetailScreen
import com.tomtruyen.pokedex.ui.shared.components.Message
import com.tomtruyen.pokedex.ui.shared.components.PokedexItem
import com.tomtruyen.pokedex.ui.shared.components.toolbar.BackToolbar
import com.tomtruyen.pokedex.utils.viewModelFactory
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: FavoriteScreenViewModel = viewModel(factory = viewModelFactory {
        FavoriteScreenViewModel(repository = get())
    })

    BoxWithConstraints {
        if(maxWidth < integerResource(id = R.integer.large_screen_size).dp) {
            FavoriteScreenContent(viewModel = viewModel, navController = navController)
        } else {
            var selectedId by remember { mutableStateOf<String?>(null) }

            Row {
                FavoriteScreenContent(
                    navController = navController,
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f),
                    onClickPokemon = {
                        selectedId = it.toString()
                    }
                )
                DetailScreen(
                    navController = navController,
                    pokemonParam = selectedId,
                    modifier = Modifier.weight(2f),
                    onClickPokemon = {
                        selectedId = it.toString()
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteScreenContent(
    navController: NavHostController,
    viewModel: FavoriteScreenViewModel,
    modifier: Modifier = Modifier,
    onClickPokemon: ((Int) -> Unit)? = null
) {
    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val pokemon by remember { viewModel.pokemon }

    CollapsingToolbarScaffold(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(101, 203, 154),
                        Color(21, 208, 220),
                    ),
                )
            ),
        state = toolbarScaffoldState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            // Calculate the textSize based on the current state of the toolbar
            val textSize =
                (20 + (34 - 12) * toolbarScaffoldState.toolbarState.progress).sp

            BackToolbar(
                title = "Favorieten",
                textSize = textSize,
                navController = navController,
            )
        }
    ) {
        if (pokemon.isEmpty()) {
            Message(text = "U heeft nog geen favorieten toegevoegd")
        } else {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                itemsIndexed(items = pokemon) { _, entry ->
                    PokedexItem(
                        pokemon = entry,
                        navController = navController,
                        elevation = 0.dp,
                        onClick = onClickPokemon
                    )
                }
            }
        }
    }
}