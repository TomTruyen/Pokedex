package com.tomtruyen.pokedex.ui.screens.home
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.enums.Sort
import com.tomtruyen.pokedex.ui.shared.components.*
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

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
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 0.dp,
                        title = { },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_filter),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.dark_one)
                                )
                            }
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        sheetState.show()
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_sort),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.dark_one)
                                )
                            }
                        }
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(bottom = 20.dp),
                            text = "Pokédex",
                            style = TextStyle(
                                fontSize = 34.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_one)
                            )
                        )
                        Search(
                            value = searchQuery,
                            placeholder = "Pokémon zoeken",
                            onValueChange =  {
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
                                    .padding(end = 4.dp)
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
                                    .padding(start = 4.dp)
                            )
                        }
                        LazyColumn {
                            itemsIndexed(items = pokemon) { _, entry ->
                                PokedexItem(pokemon = entry, navController = navController)
                            }
                        }
                    }
                }
            )
        }

    }
}