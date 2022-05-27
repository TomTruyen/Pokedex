package com.tomtruyen.pokedex.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tomtruyen.pokedex.utils.converters.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "pokemon_details")
@TypeConverters(
    ListPokemonTypeConverter::class,
    ListPokemonAbilityConverter::class,
    ListPokemonStatisticConverter::class,
    ListPokemonMoveConverter::class,
    MapStringAnyTypeConverter::class
)
data class PokemonDetails(
    @PrimaryKey
    @NotNull
    val id: Int,
    val name: String,
    val abilities: List<PokemonAbility>,
    val baseExperience: Int,
    val sprites: Map<String, Any>,
    val types: List<PokemonType>,
    val stats: List<PokemonStatistic>,
    val moves: List<PokemonMove>,
    val height: Int,
    val weight: Int
)
