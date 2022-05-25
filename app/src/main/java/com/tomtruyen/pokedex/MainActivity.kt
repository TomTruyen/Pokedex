package com.tomtruyen.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tomtruyen.pokedex.ui.screens.home.HomeScreen
import com.tomtruyen.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()

                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setStatusBarColor(MaterialTheme.colors.background)

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen()
                        }
                    }
                }

            }
        }
    }
}