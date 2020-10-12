package com.example.covidkotlinproject.api.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidkotlinproject.api.local.entity.MainModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianModelEntity

@Dao
interface TunisianDao {

    @Query("SELECT * FROM tunisianmodelentity")
    fun all(): LiveData<TunisianModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(model: TunisianModelEntity)

    @Query("DELETE FROM tunisianmodelentity")
    fun delete()

}