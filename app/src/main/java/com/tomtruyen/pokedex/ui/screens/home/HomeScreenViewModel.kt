package com.tomtruyen.pokedex.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.service.PokemonApi
import kotlinx.coroutines.launch

class HomeScreenViewModel() : ViewModel() {
    var _pokemon = listOf<Pokemon>()
    var pokemon = mutableStateOf<List<Pokemon>>(listOf())
    var error = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(true)
    var searchQuery = mutableStateOf<String>("")

    init {
        viewModelScope.launch {
            isLoading.value = true

            try {
                _pokemon = PokemonApi.service.getAll()
                pokemon.value = _pokemon.map { it.copy() }
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            isLoading.value = false
        }
    }

    fun search(value: String): Unit {
        searchQuery.value = value

        pokemon.value = _pokemon.filter {
            it.id.toString().contains(value) || it.name.contains(value)
        }
    }
}