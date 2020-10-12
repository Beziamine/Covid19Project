package com.example.covidkotlinproject.api.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.covidkotlinproject.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class CountriesModelEntity {
    @PrimaryKey
    @NonNull
    var title : String? = null
    var code : String? = null
    var total_cases : Int? = null
    var total_recovered : Int? = null
    var total_unresolved : Int? = null
    var total_deaths : Int? = null
    var total_new_cases_today : Int? = null
    var total_new_deaths_today : Int? = null
    var total_active_cases : Int? = null
    var total_serious_cases : Int? = null
    var closed_cases : String? = null
}