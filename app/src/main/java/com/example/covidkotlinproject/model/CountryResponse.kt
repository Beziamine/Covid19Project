package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class CountryResponse (

    @SerializedName("sitedata") val sitedata : List<Sitedata>,
    @SerializedName("countryitems") val countryitems : List<CountryItems>

)
