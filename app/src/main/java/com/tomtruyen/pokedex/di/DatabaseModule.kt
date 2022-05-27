package com.tomtruyen.pokedex.di

import androidx.room.Room
import com.tomtruyen.pokedex.database.AppDatabase
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication
import com.tomtruyen.pokedex.R

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().pokemonDao()
    }

    single {
        get<AppDatabase>().pokemonDetailsDao()
    }

    single {
        get<AppDatabase>().favoritePokemonDao()
    }
}