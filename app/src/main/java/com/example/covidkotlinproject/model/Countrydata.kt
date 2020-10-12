package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Countrydata (

    @SerializedName("info") val info : InfoDetail,
    @SerializedName("total_cases") val total_cases : Int,
    @SerializedName("total_recovered") val total_recovered : Int,
    @SerializedName("total_unresolved") val total_unresolved : Int,
    @SerializedName("total_deaths") val total_deaths : Int,
    @SerializedName("total_new_cases_today") val total_new_cases_today : Int,
    @SerializedName("total_new_deaths_today") val total_new_deaths_today : Int,
    @SerializedName("total_active_cases") val total_active_cases : Int,
    @SerializedName("total_serious_cases") val total_serious_cases : Int,
    @SerializedName("total_danger_rank") val total_danger_rank : Int
)