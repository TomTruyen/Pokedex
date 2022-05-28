package com.tomtruyen.pokedex.ui.screens.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.repository.FavoriteRepository
import com.tomtruyen.pokedex.database.repository.PokemonRepository
import com.tomtruyen.pokedex.database.repository.TeamRepository
import com.tomtruyen.pokedex.enums.Sort
import com.tomtruyen.pokedex.event.RxEvent
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.service.PokemonApi
import com.tomtruyen.pokedex.utils.NetworkUtils
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Suppress("StaticFieldLeak")
class HomeScreenViewModel(
    private val context: Context,
    private val repository: PokemonRepository,
    private val favoriteRepository: FavoriteRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {
    // Basic
    private var _pokemon = listOf<Pokemon>()
    var pokemon = mutableStateOf<List<Pokemon>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    // Team & card count
    var teamCount = mutableStateOf(0)
    var favoriteCount = mutableStateOf(0)

    // Filters
    var searchQuery = mutableStateOf("")
    var sort = mutableStateOf(Sort.NUMERIC_ASC)
    var filterTypes = mutableStateOf<List<String>>(listOf())

    lateinit var teamDisposable: Disposable
    lateinit var favoriteDisposable: Disposable

    init {
        load()

        teamDisposable = RxBus.listen(RxEvent.RefreshTeam::class.java).subscribe {
            loadTeamCount()
        }

        favoriteDisposable = RxBus.listen(RxEvent.RefreshFavorites::class.java).subscribe {
            loadFavoriteCount()
        }
    }

    fun destroy() {
        if(!teamDisposable.isDisposed) teamDisposable.dispose()
        if(!favoriteDisposable.isDisposed) favoriteDisposable.dispose()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                loadFavoriteCount()
                loadTeamCount()

                if (NetworkUtils.hasInternetConnection(context.applicationContext)) {
                    _pokemon = PokemonApi.service.getAll()

                    // Save data into Room (SQLite) for caching
                    coroutineScope {
                        repository.save(_pokemon)
                    }
                } else {
                    coroutineScope {
                        _pokemon = repository.getAll()
                    }
                }

                pokemon.value = _pokemon.toList()
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            }

            isLoading.value = false
        }
    }

    fun search(value: String) {
        searchQuery.value = value
        filter()
    }

    fun sort(newSort: Sort) {
        sort.value = newSort
        filter()
    }

    fun filterByTypes(type: String) {
        if (filterTypes.value.contains(type)) {
            filterTypes.value = filterTypes.value.filter { it != type }
        } else {
            val types = filterTypes.value.toMutableList()
            types.add(type)
            filterTypes.value = types.toList()
        }

        filter()
    }

    fun clearFilterByTypes() {
        filterTypes.value = listOf()

        filter()
    }

    private fun filter() {
        var filteredPokemon = _pokemon

        if (searchQuery.value.isNotEmpty()) {
            filteredPokemon = filteredPokemon.filter {
                it.id.toString().contains(searchQuery.value) || it.name.contains(searchQuery.value)
            }
        }

        if (filterTypes.value.isNotEmpty()) {
            filteredPokemon = filteredPokemon.filter { pokemon ->
                pokemon.types.any { filterTypes.value.contains(it.type["name"]) }
            }
        }

        filteredPokemon = when (sort.value) {
            Sort.ALPHABETIC_ASC -> filteredPokemon.sortedBy { pokemon -> pokemon.name }
            Sort.ALPHABETIC_DESC -> filteredPokemon.sortedByDescending { pokemon -> pokemon.name }
            Sort.NUMERIC_ASC -> filteredPokemon.sortedBy { pokemon -> pokemon.id }
            Sort.NUMERIC_DESC -> filteredPokemon.sortedByDescending { pokemon -> pokemon.id }
        }

        pokemon.value = filteredPokemon
    }

    fun loadFavoriteCount() {
        viewModelScope.launch {
            favoriteCount.value = favoriteRepository.count()
        }
    }

    fun loadTeamCount() {
        viewModelScope.launch {
            teamCount.value = teamRepository.count()
        }
    }
}