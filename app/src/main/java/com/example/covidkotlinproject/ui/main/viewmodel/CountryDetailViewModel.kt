package com.example.covidkotlinproject.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidkotlinproject.api.remote.ApiService
import com.example.covidkotlinproject.model.CountryDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class CountryDetailViewModel(private val apiService: ApiService) : ViewModel() {


    private val detailCountryLiveData = MutableLiveData<CountryDetailResponse>()
    val getCountryDetailLiveData: LiveData<CountryDetailResponse>
        get() = detailCountryLiveData


    fun getCountryDetailByCode(countryCode: String){
        val call = apiService.getCountryDetail(countryCode)
        call.enqueue(object : Callback<CountryDetailResponse> {
            override fun onResponse(
                call: Call<CountryDetailResponse>?,
                response: Response<CountryDetailResponse>
            ) {
                response?.body()?.let { detailCountry : CountryDetailResponse ->
                    thread {
                        detailCountryLiveData.postValue(detailCountry)
                    }
                }
            }

            override fun onFailure(call: Call<CountryDetailResponse>, t: Throwable?) {
                detailCountryLiveData.postValue(null)

            }
        })
    }

}