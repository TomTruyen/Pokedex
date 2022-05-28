package com.tomtruyen.pokedex.ui.screens.detail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.dao.FavoritePokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDetailsDao
import com.tomtruyen.pokedex.models.*
import com.tomtruyen.pokedex.service.PokemonApi
import com.tomtruyen.pokedex.utils.NetworkUtils
import com.tomtruyen.pokedex.utils.PokemonUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Suppress("StaticFieldLeak")
class DetailScreenViewModel(
    private val context: Context,
    private val id: Int?,
    private val dao: PokemonDetailsDao,
    private val pokemonDao: PokemonDao,
    private val favoritePokemonDao: FavoritePokemonDao
): ViewModel() {
    var pokemon = mutableStateOf<PokemonDetails?>(null);
    var error = mutableStateOf("")
    var isFavorite = mutableStateOf(false)
    var isLoading = mutableStateOf(true)
    var isRefreshing = mutableStateOf(false)
    var moves = mutableStateOf<List<PokemonMove>>(listOf())

    init {
        load()
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val pokemon = pokemon.value
            if (pokemon != null) {
                if(isFavorite.value) {
                    favoritePokemonDao.delete(pokemon.id)
                } else {
                    favoritePokemonDao.save(
                        FavoritePokemon(
                            id = pokemon.id,
                            name = pokemon.name,
                            sprites = pokemon.sprites,
                            types = pokemon.types
                        )
                    )
                }
            }
        }

        isFavorite.value = !isFavorite.value
    }

    fun refresh() {
        isRefreshing.value = true
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                if(id == null) throw Exception()

                isFavorite.value = favoritePokemonDao.exists(id)

                if(NetworkUtils.hasInternetConnection(context.applicationContext)) {
                    pokemon.value = PokemonApi.service.getById(id)

                    if(pokemon.value != null) {
                        // Get evolutions
                        PokemonApi.service.getSpecies(id).evolutionChain.run {
                            val url = this["url"]

                            if(url != null) {
                                PokemonApi.service.getEvolutionChain(url).run {
                                    pokemon.value!!.evolutions = PokemonUtils.getListOfEvolutions(
                                        pokemonDao,
                                        this.chain
                                    )
                                }
                            }
                        }

                        // Save detail to database
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

            if(isLoading.value) isLoading.value = false
            if(isRefreshing.value) isRefreshing.value = false
        }
    }
}