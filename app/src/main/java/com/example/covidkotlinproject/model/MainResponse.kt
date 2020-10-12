package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class MainResponse (
    @SerializedName("results") val results : List<MainModel>,
    @SerializedName("stat") val stat : String
)