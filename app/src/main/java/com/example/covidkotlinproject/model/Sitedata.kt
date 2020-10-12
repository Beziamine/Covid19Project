package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Sitedata (
    @SerializedName("info") val info : Info
)