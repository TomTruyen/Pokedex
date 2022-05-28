package com.tomtruyen.pokedex.database.dao

import androidx.room.*
import com.tomtruyen.pokedex.models.TeamPokemon

@Dao
interface TeamPokemonDao {
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