package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class Info (
    @SerializedName("source") val source : String
)