package com.tomtruyen.pokedex.ui.screens.home
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.shared.components.*

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {
    val pokemon by remember { viewModel.pokemon }
    val isLoading by remember { viewModel.isLoading }
    val error by remember { viewModel.error }

    if(isLoading) {
        Loader()
    } else if (error.isNotEmpty()) {
        Error(error = error)
    } else {
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
                         IconButton(onClick = { /*TODO*/ }) {
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
                    Search(value = "Hi", placeholder = "Pokémon zoeken")
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
                            PokedexItem(pokemon = entry)
                        }
                    }
                }
            }
        )
    }
}