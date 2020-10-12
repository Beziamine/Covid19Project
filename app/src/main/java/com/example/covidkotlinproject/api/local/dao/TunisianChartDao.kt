package com.example.covidkotlinproject.api.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidkotlinproject.api.local.entity.TunisianChartModelEntity
import com.example.covidkotlinproject.model.ModelChartExemple

@Dao
interface TunisianChartDao {
    @Query("SELECT * FROM tunisianchartmodelentity")
    fun allCharts(): LiveData<List<TunisianChartModelEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharts(model: List<TunisianChartModelEntity>)

    @Query("DELETE FROM tunisianchartmodelentity" )
    fun deleteCharts()
}