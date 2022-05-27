package com.tomtruyen.pokedex.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomtruyen.pokedex.database.dao.FavoritePokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDetailsDao
import com.tomtruyen.pokedex.models.FavoritePokemon
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails

@Database(
    entities = [Pokemon::class, PokemonDetails::class, FavoritePokemon::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailsDao(): PokemonDetailsDao
    abstract fun favoritePokemonDao(): FavoritePokemonDao
}