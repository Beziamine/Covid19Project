package com.example.covidkotlinproject.api.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countriesmodelentity")
    fun allCountries(): LiveData<List<CountriesModelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCountries(model: List<CountriesModelEntity>)

    @Query("DELETE FROM countriesmodelentity" )
    fun deleteCountries()
}