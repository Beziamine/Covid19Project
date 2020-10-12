package com.example.covidkotlinproject.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidkotlinproject.api.local.dao.TunisianChartDao
import com.example.covidkotlinproject.api.local.dao.TunisianDao
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianChartModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianModelEntity
import com.example.covidkotlinproject.api.remote.ApiService
import com.example.covidkotlinproject.model.CountryDetailResponse
import com.example.covidkotlinproject.model.ModelChartExemple
import com.example.covidkotlinproject.model.TunisianDetailChart
import com.example.covidkotlinproject.model.TunisianDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class TunisianDetailViewModel (private val tunisianDao: TunisianDao, private val tunisianChartDao: TunisianChartDao) : ViewModel() {


    fun getTunisianDetailByCode(): LiveData<TunisianModelEntity> {
        return tunisianDao.all()
    }

    fun getTunisianDetail(): LiveData<List<TunisianChartModelEntity>>{
       return tunisianChartDao.allCharts()
    }

}