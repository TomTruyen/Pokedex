package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.Pokemon

class ListPokemonConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Pokemon>?>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<Pokemon>? {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<Pokemon?>?): String {
        return gson.toJson(list, type)
    }
}