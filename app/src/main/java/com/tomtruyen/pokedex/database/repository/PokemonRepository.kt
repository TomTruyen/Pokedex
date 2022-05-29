package com.tomtruyen.pokedex.database.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tomtruyen.pokedex.models.Pokemon

@Dao
interface PokemonRepository {
    @Query("SELECT * FROM pokemon")
    suspend fun getAll(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    suspend fun getByName(name: String): Pokemon?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: List<Pokemon>)

}