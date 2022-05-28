package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapStringTypeConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Map<String, String>>() {}.type

    @TypeConverter
    fun fromString(json: String?): Map<String, String> {

        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(map: Map<String, String>): String {

        return gson.toJson(map, type)
    }
}