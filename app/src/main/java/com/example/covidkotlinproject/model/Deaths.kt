package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Deaths (

    @SerializedName("value") val value : Int,
    @SerializedName("detail") val detail : String
)