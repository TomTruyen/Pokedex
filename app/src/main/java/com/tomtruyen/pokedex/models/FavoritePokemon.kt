package com.tomtruyen.pokedex.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tomtruyen.pokedex.utils.converters.ListPokemonTypeConverter
import com.tomtruyen.pokedex.utils.converters.MapStringAnyTypeConverter
import com.tomtruyen.pokedex.utils.converters.MapStringTypeConverter

@Entity(tableName = "favorite_pokemon")
@TypeConverters(ListPokemonTypeConverter::class, MapStringAnyTypeConverter::class)
data class FavoritePokemon(
    @PrimaryKey
    @NonNull
    override val id: Int,
    override val name: String,
    override val sprites: Map<String, Any>,
    override val types: List<PokemonType>
) : BasePokemon