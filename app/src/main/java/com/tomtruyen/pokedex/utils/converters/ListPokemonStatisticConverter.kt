package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonStatistic

class ListPokemonStatisticConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<PokemonStatistic>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<PokemonStatistic> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<PokemonStatistic?>?): String {
        return gson.toJson(list, type)
    }
}