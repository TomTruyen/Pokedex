package com.tomtruyen.pokedex.database.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tomtruyen.pokedex.models.TeamPokemon

@Dao
interface TeamRepository {
    @Query("SELECT * FROM team_pokemon")
    suspend fun getAll(): List<TeamPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: TeamPokemon)

    @Query("DELETE FROM team_pokemon WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM team_pokemon WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT COUNT(*) FROM team_pokemon")
    suspend fun count(): Int
}