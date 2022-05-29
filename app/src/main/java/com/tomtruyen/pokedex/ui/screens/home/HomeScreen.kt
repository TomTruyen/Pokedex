package com.tomtruyen.pokedex.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.screens.Screens
import com.tomtruyen.pokedex.ui.screens.detail.DetailScreen
import com.tomtruyen.pokedex.ui.shared.components.*
import com.tomtruyen.pokedex.ui.shared.components.sheets.FilterTypeBottomSheet
import com.tomtruyen.pokedex.ui.shared.components.toolbar.HomeToolbar
import com.tomtruyen.pokedex.enums.ViewState
import com.tomtruyen.pokedex.utils.viewModelFactory
import com.tomtruyen.pokedex.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, pokemonParam: String?) {
    val viewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory {
        HomeScreenViewModel(
            context = LocalContext.current,
            repository = get(),
            favoriteRepository = get(),
            teamRepository = get(),
        )
    })

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    val state by remember { viewModel.state }
    val error by remember { viewModel.error }

    SideEffect {
        // Reloads the favorite count on resume
        // RxJava is used for tablets (split-screen)
        // On normal phones the viewModel is removed when the screen is navigated
        // This is a workaround to make sure the data is updated
        viewModel.loadFavoriteCount()
        viewModel.loadTeamCount()

        val color = if(isDarkTheme) Color.Black else Color.White
        systemUiController.setStatusBarColor(color = color)
    }

    DisposableEffect(key1 = viewModel) {
        onDispose {
            viewModel.destroy()
        }
    }


    when (state) {
        ViewState.LOADING -> {
            Loader()
        }
        ViewState.ERROR -> {
            Error(error = error)
        }
        else -> {
            BoxWithConstraints {
                if (maxWidth < integerResource(id = R.integer.large_screen_size).dp) {
                    HomeScreenContent(navController = navController, viewModel = viewModel)
                } else {
                    var selectedId by remember { mutableStateOf<String?>(pokemonParam) }

                    Row {
                        HomeScreenContent(
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
    }
}

@ExperimentalMaterialApi
@Composable
private fun HomeScreenContent(
    navController: NavHostController,
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier,
    onClickPokemon: ((Int) -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    // Switch of which sheet is active in the ModalBottomSheetScaffold
    var isSheetTypeSort by remember {
        mutableStateOf(false)
    }

    val pokemon by remember { viewModel.pokemon }

    val teamCount by remember { viewModel.teamCount }
    val favoriteCount by remember { viewModel.favoriteCount }

    val searchQuery by remember { viewModel.searchQuery }
    val sort by remember { viewModel.sort }
    val filterTypes by remember { viewModel.filterTypes }

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = Color.White,
        sheetContent = {
            if (isSheetTypeSort) {
                FilterTypeBottomSheet(
                    state = sheetState,
                    filterTypes = filterTypes,
                    onClick = { viewModel.filterByTypes(it) },
                    onClear = { viewModel.clearFilterByTypes() }
                )
            } else {
                SortBottomSheet(
                    state = sheetState,
                    value = sort,
                    onSort = {
                        viewModel.sort(it)
                    }
                )
            }
        },
    ) {
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            state = toolbarScaffoldState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                // Calculate the textSize based on the current state of the toolbar
                val textSize = (20 + (34 - 12) * toolbarScaffoldState.toolbarState.progress).sp

                HomeToolbar(
                    textSize = textSize,
                    onTypeFilterClick = {
                        coroutineScope.launch {
                            isSheetTypeSort = true
                            sheetState.show()
                        }
                    },
                    onSortClick = {
                        coroutineScope.launch {
                            isSheetTypeSort = false
                            sheetState.show()
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Search(
                    value = searchQuery,
                    placeholder = "Pokémon zoeken",
                    onValueChange = {
                        viewModel.search(it)
                    }
                )
                Row(
                    modifier = Modifier.padding(vertical = 20.dp)
                ) {
                    MenuCard(
                        title = "Mijn team",
                        subtitle = "$teamCount pokemons",
                        colors = listOf(
                            Color(70, 70, 156),
                            Color(126, 50, 224),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        onClick = { navController.navigate(Screens.Team.route) }
                    )
                    MenuCard(
                        title = "Favorieten",
                        subtitle = "$favoriteCount pokemons",
                        colors = listOf(
                            Color(101, 203, 154),
                            Color(21, 208, 220),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        onClick = { navController.navigate(Screens.Favorites.route) }
                    )
                }
                LazyColumn {
                    itemsIndexed(items = pokemon) { _, entry ->
                        PokedexItem(
                            pokemon = entry,
                            navController = navController,
                            onClick = onClickPokemon,
                        )
                    }
                }
            }

        }
    }
}