package com.tomtruyen.pokedex.ui.screens.team

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomtruyen.pokedex.database.repository.TeamRepository
import com.tomtruyen.pokedex.models.TeamPokemon
import kotlinx.coroutines.launch

class TeamScreenViewModel(
    repository: TeamRepository
) : ViewModel() {
    var pokemon = mutableStateOf<List<TeamPokemon>>(listOf())

    init {
        viewModelScope.launch {
            pokemon.value = repository.getAll()
        }
    }
}