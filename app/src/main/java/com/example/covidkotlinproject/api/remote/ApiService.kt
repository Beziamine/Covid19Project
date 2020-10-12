package com.example.covidkotlinproject.api.remote

import com.example.covidkotlinproject.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET(Routes.GET_MAIN)
    fun getMain(): Call<MainResponse>

    @GET(Routes.GET_COUNTRIES)
    fun getCountries(): Call<CountryResponse>

    @GET(Routes.GET_COUNTRY_DETAIL)
    fun getCountryDetail(@Query("countryTotal") countryTotal : String): Call<CountryDetailResponse>

    @GET(Routes.GET_TUNISIAN_DETAIL)
    fun getTunisianDetail(): Call<TunisianDetailResponse>

}