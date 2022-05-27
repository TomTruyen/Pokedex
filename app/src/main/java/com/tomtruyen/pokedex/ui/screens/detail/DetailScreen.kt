package com.tomtruyen.pokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tomtruyen.pokedex.ui.shared.components.*
import com.tomtruyen.pokedex.utils.PokemonUtils
import com.tomtruyen.pokedex.utils.viewModelFactory
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@Composable
fun DetailScreen(navController: NavHostController, id: Int?) {
    val viewModel: DetailScreenViewModel = viewModel(factory = viewModelFactory {
        DetailScreenViewModel(
            context = LocalContext.current,
            id = id,
            dao = get(),
            favoritePokemonDao = get()
        )
    })

    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val pokemon by remember { viewModel.pokemon }
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }
    val isRefreshing by remember { viewModel.isRefreshing }
    val isFavorite by remember { viewModel.isFavorite }
    val moves by remember { viewModel.moves }


    if(isLoading) {
        Loader()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { viewModel.refresh() }
        ) {
            if (error.isNotEmpty()) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { },
                            elevation = 0.dp,
                            backgroundColor = Color.White,
                            navigationIcon = {
                                IconButton(
                                    onClick = { navController.popBackStack() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                            }
                        )
                    }
                ) {
                    Error(error = error)
                }
            } else {
                pokemon?.let {
                    CollapsingToolbarScaffold(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = PokemonUtils.getDetailBackgroundGradient(
                                        it.types.first().type["name"] ?: ""
                                    ),
                                )
                            ),
                        state = toolbarScaffoldState,
                        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                        toolbar = {
                            // Calculate the textSize based on the current state of the toolbar
                            val textSize =
                                (20 + (34 - 12) * toolbarScaffoldState.toolbarState.progress).sp

                            DetailToolbar(
                                pokemon = it,
                                textSize = textSize,
                                navController = navController,
                                isFavorite = isFavorite,
                                onFavorite = {
                                    viewModel.toggleFavorite()
                                }
                            )
                        }
                    ) {

                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(20.dp)
                        ) {
                            ImageCarousel(pokemon = it)
                            AboutCard(pokemon = it)
                            StatisticsCard(pokemon = it)
                            MovesCard(moves = moves)
                        }
                    }
                }
            }
        }
    }
}