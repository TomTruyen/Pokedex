package com.tomtruyen.pokedex.ui.screens.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.shared.components.*
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomtruyen.pokedex.ui.screens.Screens
import com.tomtruyen.pokedex.utils.viewModelFactory
import org.koin.androidx.compose.get

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory {
        HomeScreenViewModel(
            context = LocalContext.current,
            dao = get()
        )
    })

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val toolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    val pokemon by remember { viewModel.pokemon }
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }
    val searchQuery by remember { viewModel.searchQuery }
    val sort by remember { viewModel.sort }


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
                SortBottomSheet(
                    state = sheetState,
                    value = sort,
                    onSort = {
                        viewModel.sort(it)
                    }
                )
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
                        state = sheetState,
                        textSize = textSize
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
                                subtitle = "x pokemons",
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
                                subtitle = "x pokemons",
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