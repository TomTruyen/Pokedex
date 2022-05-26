package com.tomtruyen.pokedex.ui.screens.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.shared.components.Error
import com.tomtruyen.pokedex.ui.shared.components.Loader
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailScreenViewModel) {
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
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                // TODO
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        ) {
            pokemon?.let { it1 -> Text(text = it1.name) }
        }
    }
}