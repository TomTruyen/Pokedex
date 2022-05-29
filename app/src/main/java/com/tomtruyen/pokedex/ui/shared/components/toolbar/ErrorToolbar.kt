package com.tomtruyen.pokedex.ui.shared.components.toolbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.R

@Composable
fun ErrorToolbar(navController: NavHostController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    TopAppBar(
        title = { },
        elevation = 0.dp,
        backgroundColor = Color.White,
        navigationIcon = {
            if(screenWidth < integerResource(id = R.integer.large_screen_size).dp) {
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
        }
    )
}