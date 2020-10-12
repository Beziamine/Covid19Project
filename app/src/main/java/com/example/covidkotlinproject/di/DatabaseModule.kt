package com.example.covidkotlinproject.di

import androidx.room.Room
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.api.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().baseContext.getString(R.string.app_name)
        ).build()
    }

    single {
        get<AppDatabase>().modelDAO()
    }

    single {
        get<AppDatabase>().countryDAO()
    }

    single {
        get<AppDatabase>().tunisianDAO()
    }

    single {
        get<AppDatabase>().tunisianChartDAO()
    }
}