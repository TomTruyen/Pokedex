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

    var activeSort by remember {
        mutableStateOf(sort)
    }

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
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Sorteren op",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_one)
                            )
                        )
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    // Set the actual viewModel sort value on closing
                                    // Otherwise it remembers the state and displays an incorrect
                                    // SortItem as selected due to previous actions
                                    activeSort = sort
                                    sheetState.hide()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                tint = colorResource(id = R.color.light_grey)
                            )
                        }
                    }

                    SortItem(
                        icon = R.drawable.ic_alphabetic_asc,
                        text = "Alfabetisch oplopend",
                        selected = activeSort == Sort.ALPHABETIC_ASC,
                        onClick = { activeSort = Sort.ALPHABETIC_ASC },
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    SortItem(
                        icon = R.drawable.ic_alphabetic_desc,
                        text = "Alfabetisch aflopend",
                        selected = activeSort == Sort.ALPHABETIC_DESC,
                        onClick = { activeSort = Sort.ALPHABETIC_DESC },
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    SortItem(
                        icon = R.drawable.ic_numeric_asc,
                        text = "Numeriek oplopend",
                        selected = activeSort == Sort.NUMERIC_ASC,
                        onClick = { activeSort = Sort.NUMERIC_ASC },
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    SortItem(
                        icon = R.drawable.ic_numeric_desc,
                        text = "Numeriek aflopend",
                        selected = activeSort == Sort.NUMERIC_DESC,
                        onClick = { activeSort = Sort.NUMERIC_DESC },
                        modifier = Modifier.padding(vertical = 5.dp)
                    )

                    PrimaryButton(
                        text = "Toepassen",
                        onClick = {
                            viewModel.sort(activeSort)
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                        },
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
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