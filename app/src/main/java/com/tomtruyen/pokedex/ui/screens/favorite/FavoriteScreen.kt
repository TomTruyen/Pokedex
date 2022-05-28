package com.tomtruyen.pokedex.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.ui.shared.components.PokedexItem
import com.tomtruyen.pokedex.utils.viewModelFactory
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.tomtruyen.pokedex.ui.shared.components.Message
import com.tomtruyen.pokedex.ui.shared.components.toolbar.BackToolbar

@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: FavoriteScreenViewModel = viewModel(factory = viewModelFactory {
        FavoriteScreenViewModel(repository = get())
    })

    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val pokemon by remember { viewModel.pokemon }

    CollapsingToolbarScaffold(
        modifier = Modifier
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
        if(pokemon.isEmpty()) {
            Message(text = "U heeft nog geen favorieten toegevoegd")
        } else {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                itemsIndexed(items = pokemon) { _, entry ->
                    PokedexItem(pokemon = entry, navController = navController, elevation = 0.dp)
                }
            }
        }
    }
}