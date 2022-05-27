package com.tomtruyen.pokedex.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.tomtruyen.pokedex.database.dao.PokemonDao
import com.tomtruyen.pokedex.database.dao.PokemonDetailsDao
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails

@Database(
    entities = [Pokemon::class, PokemonDetails::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonDetailsDao(): PokemonDetailsDao
}