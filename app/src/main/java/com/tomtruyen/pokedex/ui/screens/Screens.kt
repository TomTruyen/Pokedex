package com.tomtruyen.pokedex.ui.screens

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Details : Screens("details/{pokemon}")
    object Favorites : Screens("favorites")
    object Team : Screens("team")
}

class ScreenUtils {
    companion object {
        fun toDetail(pokemonId: Int): String {
            return Screens.Details.route.replace("{pokemon}", pokemonId.toString())
        }

        fun getDeepLinks(): List<NavDeepLink> {
            return listOf(navDeepLink { uriPattern = "pokedex://details/{pokemon}" })
        }

        fun getDetailDeepLink(name: String): String {
            return "pokedex://details/$name"
        }
    }
}

