package com.tomtruyen.pokedex.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tomtruyen.pokedex.utils.converters.ListPokemonTypeConverter
import com.tomtruyen.pokedex.utils.converters.MapStringTypeConverter


@Entity
@TypeConverters(ListPokemonTypeConverter::class, MapStringTypeConverter::class)
data class Pokemon(
    @PrimaryKey
    @NonNull
    override val id: Int,
    override val name: String,
    override val sprites: Map<String, String>,
    override val types: List<PokemonType>,
 ) : BasePokemon