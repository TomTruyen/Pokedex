package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonMove

class ListPokemonMoveConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<PokemonMove>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<PokemonMove> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<PokemonMove?>?): String {
        return gson.toJson(list, type)
    }
}