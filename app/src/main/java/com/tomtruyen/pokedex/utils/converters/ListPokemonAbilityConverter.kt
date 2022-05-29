package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonAbility

class ListPokemonAbilityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<PokemonAbility>>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<PokemonAbility> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<PokemonAbility?>?): String {
        return gson.toJson(list, type)
    }
}