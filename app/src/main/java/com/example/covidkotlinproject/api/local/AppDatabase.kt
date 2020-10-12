package com.example.covidkotlinproject.api.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidkotlinproject.api.local.dao.CountriesDao
import com.example.covidkotlinproject.api.local.dao.MainDao
import com.example.covidkotlinproject.api.local.dao.TunisianChartDao
import com.example.covidkotlinproject.api.local.dao.TunisianDao
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.local.entity.MainModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianChartModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianModelEntity

@Database(entities = [MainModelEntity::class,CountriesModelEntity::class, TunisianModelEntity::class, TunisianChartModelEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun modelDAO(): MainDao

    abstract fun countryDAO(): CountriesDao

    abstract fun tunisianDAO(): TunisianDao

    abstract fun tunisianChartDAO(): TunisianChartDao

}