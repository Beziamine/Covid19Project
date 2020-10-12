package com.example.covidkotlinproject.api.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.local.entity.MainModelEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM mainmodelentity")
    fun all(): LiveData<MainModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(model: MainModelEntity)

    @Query("DELETE FROM mainmodelentity")
    fun delete()

}