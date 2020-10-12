package com.example.covidkotlinproject.api.remote

import com.example.covidkotlinproject.BuildConfig

class Routes {
    companion object {

        const val BASE_URL = BuildConfig.BASE_URL;

        const val GET_MAIN = "free-api?global=stats"

        const val GET_COUNTRIES = "free-api?countryTotals=ALL"

        const val GET_COUNTRY_DETAIL = "free-api"

        const val GET_TUNISIAN_DETAIL = "free-api?countryTimeline=TN"


    }
}