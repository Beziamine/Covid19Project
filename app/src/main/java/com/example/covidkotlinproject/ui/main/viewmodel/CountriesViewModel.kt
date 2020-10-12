package com.example.covidkotlinproject.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.covidkotlinproject.api.local.dao.CountriesDao
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.remote.ApiService
import com.example.covidkotlinproject.model.CountriesModel
import com.example.covidkotlinproject.model.CountryItems
import com.example.covidkotlinproject.model.CountryResponse
import com.example.covidkotlinproject.utils.NumberUtils.convertList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class CountriesViewModel(private val countriesDao: CountriesDao) : ViewModel() {

    fun getCountries(): LiveData<List<CountriesModelEntity>> {
        return countriesDao.allCountries()
    }

}