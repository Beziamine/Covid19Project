package com.example.covidkotlinproject.model

import com.google.gson.annotations.SerializedName

data class InfoDetail (

    @SerializedName("ourid") val ourid : Int,
    @SerializedName("title") val title : String,
    @SerializedName("code") val code : String,
    @SerializedName("source") val source : String

)