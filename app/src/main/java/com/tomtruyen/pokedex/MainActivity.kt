package com.tomtruyen.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tomtruyen.pokedex.ui.screens.Screens
import com.tomtruyen.pokedex.ui.screens.detail.DetailScreen
import com.tomtruyen.pokedex.ui.screens.favorite.FavoriteScreen
import com.tomtruyen.pokedex.ui.screens.home.HomeScreen
import com.tomtruyen.pokedex.ui.screens.team.TeamScreen
import com.tomtruyen.pokedex.ui.theme.PokedexTheme
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.screens.ScreenUtils

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val largeScreenSize = applicationContext.resources.getInteger(R.integer.large_screen_size)

        setContent {
            PokedexTheme {
                val navController = rememberAnimatedNavController()

                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    BoxWithConstraints {
                        val isLargeScreen = maxWidth >= largeScreenSize.dp

                        AnimatedNavHost(
                            navController = navController,
                            startDestination = Screens.Home.route
                        ) {
                            composable(
                                route = Screens.Home.route,
                                arguments = listOf(navArgument("pokemon") {
                                    type = NavType.StringType
                                }),
                                deepLinks = if(isLargeScreen) ScreenUtils.getDeepLinks() else listOf(),
                                content = {
                                    HomeScreen(
                                        navController = navController,
                                        it.arguments?.getString("pokemon")
                                    )
                                },
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
                                arguments = listOf(navArgument("pokemon") {
                                    type = NavType.StringType
                                }),
                                deepLinks = if(!isLargeScreen) ScreenUtils.getDeepLinks() else listOf(),
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
                                },
                                content = {
                                    DetailScreen(
                                        navController,
                                        it.arguments?.getString("pokemon")
                                    )
                                }
                            )

                            composable(
                                route = Screens.Favorites.route,
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
                                },
                                content = {
                                    FavoriteScreen(navController)
                                }
                            )

                            composable(
                                route = Screens.Team.route,
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
                                },
                                content = {
                                    TeamScreen(navController)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}