package com.tomtruyen.pokedex.ui.screens.detail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.dao.PokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDetailsDao
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.models.PokemonMove
import com.tomtruyen.pokedex.service.PokemonApi
import com.tomtruyen.pokedex.utils.NetworkUtils
import com.tomtruyen.pokedex.utils.PokemonUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val context: Context,
    private val id: Int?,
    private val dao: PokemonDetailsDao
): ViewModel() {
    var pokemon = mutableStateOf<PokemonDetails?>(null);
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)
    var moves = mutableStateOf<List<PokemonMove>>(listOf())

    init {
        viewModelScope.launch {
            try {
                if(id == null) throw Exception()

                if(NetworkUtils.hasInternetConnection(context)) {
                    pokemon.value = PokemonApi.service.getById(id)

                    if(pokemon.value != null) {
                        coroutineScope {
                            dao.save(pokemon.value!!)
                        }
                    }
                } else {
                    pokemon.value = dao.getById(id)
                    if(pokemon.value == null) {
                        throw Exception("Couldn't find local data. Please try connecting to the internet.")
                    }
                }

                if(pokemon.value != null) {
                    moves.value = PokemonUtils.getLevelUpMoves(pokemon.value!!.moves)
                }
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            isLoading.value = false
        }
    }
}