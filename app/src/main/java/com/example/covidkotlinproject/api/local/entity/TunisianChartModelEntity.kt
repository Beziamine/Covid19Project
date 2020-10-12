package com.example.covidkotlinproject.api.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.covidkotlinproject.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class TunisianChartModelEntity {
    @PrimaryKey
    @NonNull
    var totalConfirmed : Int = 0
    var totalDeaths : Int = 0
    var totalRecovered : Int = 0
    var deltaConfirmed : Int = 0
    var deltaRecovered : Int = 0
    var reportDate : String = ""
}