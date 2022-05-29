package com.tomtruyen.pokedex.database.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tomtruyen.pokedex.models.PokemonDetails

@Dao
interface PokemonDetailsRepository {
    @Query("SELECT * FROM pokemon_details WHERE id = :id")
    suspend fun getById(id: Int): PokemonDetails

    @Query("SELECT * FROM pokemon_details WHERE name = :name")
    suspend fun getByName(name: String): PokemonDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: PokemonDetails)
}