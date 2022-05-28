package com.tomtruyen.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomtruyen.pokedex.database.repository.FavoriteRepository
import com.tomtruyen.pokedex.database.repository.PokemonRepository
import com.tomtruyen.pokedex.database.repository.PokemonDetailsRepository
import com.tomtruyen.pokedex.database.repository.TeamRepository
import com.tomtruyen.pokedex.models.FavoritePokemon
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.models.TeamPokemon

@Database(
    entities = [Pokemon::class, PokemonDetails::class, FavoritePokemon::class, TeamPokemon::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonRepository
    abstract fun pokemonDetailsDao(): PokemonDetailsRepository
    abstract fun favoritePokemonDao(): FavoriteRepository
    abstract fun teamPokemonDao(): TeamRepository
}