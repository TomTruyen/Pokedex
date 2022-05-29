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
    ListPokemonConverter::class,
    PokemonSpritesConverter::class,
)
data class PokemonDetails(
    @PrimaryKey
    @NotNull
    override val id: Int,
    override val name: String,
    override val sprites: PokemonSprites,
    override val types: List<PokemonType>,
    val abilities: List<PokemonAbility>,
    val baseExperience: Int,
    val stats: List<PokemonStatistic>,
    val moves: List<PokemonMove>,
    val height: Int,
    val weight: Int,
    var evolutions: List<Pokemon>?,
) : BasePokemon
