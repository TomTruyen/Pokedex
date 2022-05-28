@file:OptIn(ExperimentalPagerApi::class)

package com.tomtruyen.pokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.ui.shared.Evolutions
import com.tomtruyen.pokedex.ui.shared.components.*
import com.tomtruyen.pokedex.ui.shared.components.toolbar.DetailToolbar
import com.tomtruyen.pokedex.utils.PokemonUtils
import com.tomtruyen.pokedex.utils.viewModelFactory
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@Composable
fun DetailScreen(navController: NavHostController, id: Int, modifier: Modifier = Modifier) {
    val viewModel: DetailScreenViewModel = viewModel(factory = viewModelFactory {
        DetailScreenViewModel(
            context = LocalContext.current,
            id = id,
            repository = get(),
            pokemonRepository = get(),
            favoriteRepository = get(),
            teamRepository = get()
        )
    })

    SideEffect {
        if (viewModel.id != id) {
            viewModel.id = id
            viewModel.load()
        }
    }


    val pokemon by remember { viewModel.pokemon }
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }
    val isRefreshing by remember { viewModel.isRefreshing }

    if (isLoading) {
        Loader(modifier = modifier)
    } else {
        SwipeRefresh(
            modifier = modifier,
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
                pokemon?.let { pokemon ->
                    DetailScreenContent(
                        pokemon = pokemon,
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailScreenContent(
    pokemon: PokemonDetails,
    navController: NavHostController,
    viewModel: DetailScreenViewModel
) {
    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val isFavorite by remember { viewModel.isFavorite }
    val isTeam by remember { viewModel.isTeam }
    val teamCount by remember { viewModel.teamCount }
    val moves by remember { viewModel.moves }

    Box {
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    brush = Brush.verticalGradient(
                        colors = PokemonUtils.getDetailBackgroundGradient(
                            pokemon.types.first().type["name"] ?: ""
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
                    pokemon = pokemon,
                    textSize = textSize,
                    navController = navController,
                    isFavorite = isFavorite,
                    onFavorite = {
                        viewModel.toggleFavorite()
                    }
                )
            }
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp)
                ) {
                    if (this@BoxWithConstraints.maxWidth < integerResource(id = R.integer.large_screen_size).dp) {
                        ImageCarousel(pokemon = pokemon)
                        AboutCard(pokemon = pokemon)
                        StatisticsCard(pokemon = pokemon)
                        MovesCard(moves = moves)
                        Evolutions(navController = navController, pokemon = pokemon)
                    } else {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                ImageCarousel(pokemon = pokemon)
                                AboutCard(pokemon = pokemon)
                            }

                            Spacer(modifier = Modifier.width(32.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                MovesCard(moves = moves)
                                Evolutions(navController = navController, pokemon = pokemon)
                            }
                        }

                        StatisticsCard(pokemon = pokemon)
                    }

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }

        var buttonText = "Toevoegen aan mijn team"

        if (isTeam) {
            buttonText = "Verwijderen uit mijn team"
        } else if (teamCount >= 6) {
            buttonText = "Team is vol"
        }

        PrimaryButton(
            text = buttonText,
            enabled = isTeam || teamCount < 6,
            onClick = {
                if (isTeam) {
                    viewModel.removeFromTeam()
                } else {
                    viewModel.addToTeam()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}