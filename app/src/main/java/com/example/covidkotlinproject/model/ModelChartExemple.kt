package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class ModelChartExemple (
    @SerializedName("reportDate")
    var reportDate: String = "",
    @SerializedName("totalConfirmed")
    var totalConfirmed: Int = 0,
    @SerializedName("totalDeaths")
    var totalDeaths: Int = 0,
    @SerializedName("totalRecovered")
    var totalRecovered: Int = 0,
    @SerializedName("deltaConfirmed")
    var deltaConfirmed: Int = 0,
    @SerializedName("deltaRecovered")
    var deltaRecovered: Int = 0
)