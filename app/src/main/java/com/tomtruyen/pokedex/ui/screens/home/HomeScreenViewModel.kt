package com.tomtruyen.pokedex.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.enums.Sort
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.service.PokemonApi
import kotlinx.coroutines.launch

class HomeScreenViewModel() : ViewModel() {
    var _pokemon = listOf<Pokemon>()
    var pokemon = mutableStateOf<List<Pokemon>>(listOf())
    var error = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(true)
    var searchQuery = mutableStateOf<String>("")
    var sort = mutableStateOf<Sort>(Sort.NUMERIC_ASC)

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

    fun search(value: String) {
        searchQuery.value = value

        pokemon.value = _pokemon.filter {
            it.id.toString().contains(value) || it.name.contains(value)
        }
    }

    fun sort(newSort: Sort) {
        sort.value = newSort

        pokemon.value = when(newSort) {
            Sort.ALPHABETIC_ASC -> _pokemon.sortedBy { pokemon -> pokemon.name }
            Sort.ALPHABETIC_DESC -> _pokemon.sortedByDescending { pokemon -> pokemon.name }
            Sort.NUMERIC_ASC -> _pokemon.sortedBy { pokemon -> pokemon.id }
            Sort.NUMERIC_DESC -> _pokemon.sortedByDescending { pokemon -> pokemon.id }
        }
    }
}