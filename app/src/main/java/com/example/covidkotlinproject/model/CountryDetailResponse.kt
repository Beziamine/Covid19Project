package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class CountryDetailResponse (

    @SerializedName("countrydata") val countrydata : List<Countrydata>,
    @SerializedName("stat") val stat : String
)