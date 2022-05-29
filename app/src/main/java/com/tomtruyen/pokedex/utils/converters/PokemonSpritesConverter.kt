package com.tomtruyen.pokedex.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomtruyen.pokedex.models.PokemonSprites

class PokemonSpritesConverter {
    private val gson = Gson()
    private val type = object : TypeToken<PokemonSprites>() {}.type

    @TypeConverter
    fun fromString(json: String?): PokemonSprites {

        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(sprites: PokemonSprites): String {

        return gson.toJson(sprites, type)
    }
}