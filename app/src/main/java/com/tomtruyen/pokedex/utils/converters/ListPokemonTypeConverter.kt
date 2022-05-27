package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonType

class ListPokemonTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<PokemonType>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<PokemonType> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<PokemonType?>?): String {
        return gson.toJson(list, type)
    }
}