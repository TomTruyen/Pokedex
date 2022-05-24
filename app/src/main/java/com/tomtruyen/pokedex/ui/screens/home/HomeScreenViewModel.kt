package com.tomtruyen.pokedex.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.service.PokemonApi
import kotlinx.coroutines.launch

class HomeScreenViewModel() : ViewModel() {
    var pokemon = mutableStateOf<List<Pokemon>>(listOf())
    var error = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(true)

    init {
        viewModelScope.launch {
            isLoading.value = true

            try {
                val result = PokemonApi.service.getAll()
                pokemon.value = result
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            isLoading.value = false
        }
    }
}