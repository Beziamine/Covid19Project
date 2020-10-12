package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class TunisianDetailChart (

    @SerializedName("total_cases")
    var totalCases: Int = 0,
    @SerializedName("total_deaths")
    var totalDeaths: Int = 0

)