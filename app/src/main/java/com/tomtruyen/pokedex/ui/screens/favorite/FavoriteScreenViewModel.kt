package com.tomtruyen.pokedex.ui.screens.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.dao.FavoritePokemonDao
import com.tomtruyen.pokedex.models.FavoritePokemon
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(
    dao: FavoritePokemonDao
) : ViewModel() {
    var pokemon = mutableStateOf<List<FavoritePokemon>>(listOf())

    init {
        viewModelScope.launch {
            pokemon.value = dao.getAll()
        }
    }
}