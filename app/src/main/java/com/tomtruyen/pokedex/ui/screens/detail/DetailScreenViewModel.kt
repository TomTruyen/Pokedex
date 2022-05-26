package com.tomtruyen.pokedex.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.service.PokemonApi
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val id: Int?): ViewModel() {
    var pokemon = mutableStateOf<PokemonDetails?>(null);
    var error = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(true)

    init {
        viewModelScope.launch {
            try {
                if(id == null) throw Exception()

                pokemon.value = PokemonApi.service.getById(id)
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            isLoading.value = false
        }
    }
}