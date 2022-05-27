package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonType

class MapStringAnyTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Map<String, Any>>() {}.type

    @TypeConverter
    fun fromString(json: String?): Map<String, Any> {

        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(map: Map<String, Any>): String {

        return gson.toJson(map, type)
    }
}