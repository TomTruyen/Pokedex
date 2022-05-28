package com.tomtruyen.pokedex.ui.screens.detail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.repository.FavoriteRepository
import com.tomtruyen.pokedex.database.repository.PokemonRepository
import com.tomtruyen.pokedex.database.repository.PokemonDetailsRepository
import com.tomtruyen.pokedex.database.repository.TeamRepository
import com.tomtruyen.pokedex.models.*
import com.tomtruyen.pokedex.service.PokemonApi
import com.tomtruyen.pokedex.utils.NetworkUtils
import com.tomtruyen.pokedex.utils.PokemonUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Suppress("StaticFieldLeak")
class DetailScreenViewModel(
    private val context: Context,
    private val id: Int,
    private val repository: PokemonDetailsRepository,
    private val pokemonRepository: PokemonRepository,
    private val favoriteRepository: FavoriteRepository,
    private val teamRepository: TeamRepository
): ViewModel() {
    var pokemon = mutableStateOf<PokemonDetails?>(null);
    var error = mutableStateOf("")
    var isFavorite = mutableStateOf(false)
    var isTeam = mutableStateOf(false)
    var teamCount = mutableStateOf(0)
    var isLoading = mutableStateOf(true)
    var isRefreshing = mutableStateOf(false)
    var moves = mutableStateOf<List<PokemonMove>>(listOf())

    init {
        load()
    }

    fun refresh() {
        isRefreshing.value = true
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                if(id == -1) throw Exception()

                isFavorite.value = favoriteRepository.exists(id)

                loadTeam()

                if(NetworkUtils.hasInternetConnection(context.applicationContext)) {
                    pokemon.value = PokemonApi.service.getById(id)

                    if(pokemon.value != null) {
                        // Get evolutions
                        PokemonApi.service.getSpecies(id).evolutionChain.run {
                            val url = this["url"]

                            if(url != null) {
                                PokemonApi.service.getEvolutionChain(url).run {
                                    pokemon.value!!.evolutions = PokemonUtils.getListOfEvolutions(
                                        pokemonRepository,
                                        this.chain
                                    )
                                }
                            }
                        }

                        // Save detail to database
                        coroutineScope {
                            repository.save(pokemon.value!!)
                        }
                    }
                } else {
                    pokemon.value = repository.getById(id)
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

    private fun loadTeam() {
        viewModelScope.launch {

            isTeam.value = teamRepository.exists(id)
            teamCount.value = teamRepository.count()
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val pokemon = pokemon.value
            if (pokemon != null) {
                if(isFavorite.value) {
                    favoriteRepository.delete(pokemon.id)
                } else {
                    favoriteRepository.save(
                        FavoritePokemon(
                            id = pokemon.id,
                            name = pokemon.name,
                            sprites = pokemon.sprites,
                            types = pokemon.types
                        )
                    )
                }

                isFavorite.value = favoriteRepository.exists(pokemon.id)
            }
        }
    }

    fun addToTeam() {
        viewModelScope.launch {
            val pokemon = pokemon.value
            if (pokemon != null) {
                teamRepository.save(
                    TeamPokemon(
                        id = pokemon.id,
                        name = pokemon.name,
                        sprites = pokemon.sprites,
                        types = pokemon.types
                    )
                )

                loadTeam()
            }
        }
    }

    fun removeFromTeam() {
        viewModelScope.launch {
            val pokemon = pokemon.value
            if (pokemon != null) {
                teamRepository.delete(pokemon.id)

                loadTeam()
            }
        }
    }
}