package com.tomtruyen.pokedex.database.repository

import androidx.room.*
import com.tomtruyen.pokedex.models.FavoritePokemon

@Dao
interface FavoriteRepository {
    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getAll(): List<FavoritePokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: FavoritePokemon)

    @Query("DELETE FROM favorite_pokemon WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM favorite_pokemon WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT COUNT(*) FROM favorite_pokemon")
    suspend fun count(): Int
}