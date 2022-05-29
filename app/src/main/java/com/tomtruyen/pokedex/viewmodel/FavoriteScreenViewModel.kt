package com.tomtruyen.pokedex.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.repository.FavoriteRepository
import com.tomtruyen.pokedex.models.FavoritePokemon
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(
    repository: FavoriteRepository
) : ViewModel() {
    var pokemon = mutableStateOf<List<FavoritePokemon>>(listOf())

    init {
        viewModelScope.launch {
            pokemon.value = repository.getAll()
        }
    }
}