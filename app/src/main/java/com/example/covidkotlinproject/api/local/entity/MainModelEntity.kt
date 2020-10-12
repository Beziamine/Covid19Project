package com.example.covidkotlinproject.api.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.covidkotlinproject.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class MainModelEntity {
    @PrimaryKey
    @NonNull
    var total : Int? = null
    var confirmed : Int? = null
    var recovered : Int? = null
    var deaths : Int? = null
    var newCases : Int? = null
    var newDeaths : Int? = null
}