package com.tomtruyen.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tomtruyen.pokedex.ui.screens.Screens
import com.tomtruyen.pokedex.ui.screens.detail.DetailScreen
import com.tomtruyen.pokedex.ui.screens.detail.DetailScreenViewModel
import com.tomtruyen.pokedex.ui.screens.home.HomeScreen
import com.tomtruyen.pokedex.ui.screens.home.HomeScreenViewModel
import com.tomtruyen.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeScreenViewModel = HomeScreenViewModel()

        setContent {
            PokedexTheme {
                val navController = rememberAnimatedNavController()

                Surface(
                    color = MaterialTheme.colors.background
                ) {
//                    val systemUiController = rememberSystemUiController()
//                    systemUiController.setStatusBarColor(Color.Transparent)

                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screens.Home.route
                    ) {
                        composable(
                            route = Screens.Home.route,
                            content = { HomeScreen( navController = navController, viewModel = homeScreenViewModel) },
                            exitTransition = { ->
                                slideOutHorizontally(
                                    targetOffsetX = { -300 },
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            },
                            popEnterTransition = { ->
                                slideInHorizontally(
                                    initialOffsetX = { 300 },
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            }
                        )

                        composable(
                            route = Screens.Details.route,
                            enterTransition = { ->
                                slideInHorizontally(
                                    initialOffsetX = { 300 },
                                    animationSpec = tween(300)
                                ) + fadeIn(animationSpec = tween(300))
                            },
                            popExitTransition = { ->
                                slideOutHorizontally(
                                    targetOffsetX = { -300 },
                                    animationSpec = tween(300)
                                ) + fadeOut(animationSpec = tween(300))
                            }
                        ) {
                            DetailScreen(navController, DetailScreenViewModel(it.arguments?.getString("pokemonId")?.toInt()))
                        }
                    }
                }

            }
        }
    }
}