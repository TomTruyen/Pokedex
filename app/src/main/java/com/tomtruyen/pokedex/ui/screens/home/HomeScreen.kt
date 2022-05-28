package com.tomtruyen.pokedex.ui.screens.home
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.ui.shared.components.*
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomtruyen.pokedex.ui.screens.Screens
import com.tomtruyen.pokedex.ui.shared.components.sheets.FilterTypeBottomSheet
import com.tomtruyen.pokedex.utils.viewModelFactory
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory {
        HomeScreenViewModel(
            context = LocalContext.current,
            repository = get(),
            favoriteRepository = get(),
            teamRepository = get(),
        )
    })

    SideEffect {
        // Reloads the favorite count on resume
        viewModel.loadFavoriteCount()
        viewModel.loadTeamCount()
    }

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
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    val teamCount by remember { viewModel.teamCount }
    val favoriteCount by remember { viewModel.favoriteCount }

    val searchQuery by remember { viewModel.searchQuery }
    val sort by remember { viewModel.sort }
    val filterTypes by remember { viewModel.filterTypes }


    if(isLoading) {
        Loader()
    } else if (error.isNotEmpty()) {
        Error(error = error)
    } else {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            sheetBackgroundColor = Color.White,
            sheetContent = {
                if(isSheetTypeSort) {
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
                                onClick = {  }
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
                                PokedexItem(pokemon = entry, navController = navController)
                            }
                        }
                    }

            }
        }
    }
}