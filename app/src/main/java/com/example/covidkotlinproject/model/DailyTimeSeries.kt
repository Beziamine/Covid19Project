package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class DailyTimeSeries (

    @SerializedName("pattern") val pattern : String,
    @SerializedName("example") val example : String
)