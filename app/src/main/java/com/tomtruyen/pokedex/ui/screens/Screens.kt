package com.tomtruyen.pokedex.ui.screens

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Details : Screens("details/{pokemonId}")
    object Favorites : Screens("favorites")
    object Team : Screens("team")
}

class ScreenUtils {
    companion object {
        fun toDetail(pokemonId: Int): String {
            return Screens.Details.route.replace("{pokemonId}", pokemonId.toString())
        }
    }
}

