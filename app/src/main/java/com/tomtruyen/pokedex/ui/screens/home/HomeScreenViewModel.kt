package com.tomtruyen.pokedex.ui.screens.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.dao.PokemonDao
import com.tomtruyen.pokedex.enums.Sort
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.service.PokemonApi
import com.tomtruyen.pokedex.utils.NetworkUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val context: Context,
    private val dao: PokemonDao
) : ViewModel() {
    private var _pokemon = listOf<Pokemon>()
    var pokemon = mutableStateOf<List<Pokemon>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)
    var isRefreshing = mutableStateOf(false)
    var searchQuery = mutableStateOf("")
    var sort = mutableStateOf(Sort.NUMERIC_ASC)

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                if(NetworkUtils.hasInternetConnection(context)) {
                    _pokemon = PokemonApi.service.getAll()

                    // Save data into Room (SQLite) for caching
                    coroutineScope {
                        dao.save(_pokemon)
                    }
                } else {
                    coroutineScope {
                        _pokemon = dao.getAll()
                    }
                }

                pokemon.value = _pokemon.toList()
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            if(isLoading.value) isLoading.value = false
            if(isRefreshing.value) isRefreshing.value = false
        }
    }

    fun refresh() {
        isRefreshing.value = true
        load()
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