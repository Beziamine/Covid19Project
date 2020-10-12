package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Confirmed (

    @SerializedName("value") val value : Int,
    @SerializedName("detail") val detail : String
)