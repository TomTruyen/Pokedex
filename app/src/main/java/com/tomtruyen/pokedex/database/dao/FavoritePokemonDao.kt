package com.tomtruyen.pokedex.database.dao

import androidx.room.*
import com.tomtruyen.pokedex.models.FavoritePokemon

@Dao
interface FavoritePokemonDao {
    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getAll(): List<FavoritePokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: FavoritePokemon)

    @Query("DELETE FROM favorite_pokemon WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM favorite_pokemon WHERE id = :id)")
    suspend fun exists(id: Int): Boolean
}