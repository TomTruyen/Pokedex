package com.tomtruyen.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tomtruyen.pokedex.models.Pokemon
import com.tomtruyen.pokedex.models.PokemonDetails

@Dao
interface PokemonDetailsDao {
    @Query("SELECT * FROM pokemon_details WHERE id = :id")
    suspend fun getById(id: Int): PokemonDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: PokemonDetails)
}