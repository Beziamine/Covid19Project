package com.example.covidkotlinproject.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianChartModelEntity
import com.example.covidkotlinproject.model.CountriesModel
import com.example.covidkotlinproject.model.ModelChartExemple
import java.text.NumberFormat
import java.util.*


object NumberUtils {

    fun numberFormat(number: Int?) = number?.let {
        NumberFormat.getNumberInstance(Locale.getDefault()).format(number)
    } ?: "-"

    fun extractDigit(number: String) = Regex("[^0-9]").replace(number, "").toInt()

    fun formatDate(date: String): String {

        return "Last Update : " + date.substring(0,10) + "  "+ date!!.substring(11,16) + " GMT"
    }

    fun getCountryFlag(s: String) : String {
        return "https://www.countryflags.io/" +  s + "/flat/64.png"
    }

    fun convertList(courses: List<CountriesModel>) : List<CountriesModelEntity> {
        val newList = mutableListOf<CountriesModelEntity>()
        courses.forEach {

            val countriesModelEntity = CountriesModelEntity()

            countriesModelEntity.title = it.title
            countriesModelEntity.code = it.code
            countriesModelEntity.total_cases = it.total_cases
            countriesModelEntity.total_active_cases = (it.total_cases!! - (it.total_deaths!! + it.total_recovered!!))
//            countriesModelEntity.total_active_cases = it.total_active_cases
            countriesModelEntity.total_deaths = it.total_deaths
            countriesModelEntity.total_recovered = it.total_recovered
            countriesModelEntity.total_new_cases_today = it.total_new_cases_today
            countriesModelEntity.total_new_deaths_today = it.total_new_deaths_today

            if(it.total_cases != 0)
            {
                countriesModelEntity.closed_cases = "%.2f".format(((it.total_recovered!! + it.total_deaths!!)*100.toDouble())/it.total_cases!!) + " %"
                countriesModelEntity.closed_cases_value = (((it.total_recovered!! + it.total_deaths!!)*100.toDouble())/it.total_cases!!)
            }else {
                countriesModelEntity.closed_cases = "0,00 %"
                countriesModelEntity.closed_cases_value = 0.0
            }

            newList += countriesModelEntity
        }

        return newList
    }

    fun convertListChart(courses: List<ModelChartExemple>) : List<TunisianChartModelEntity> {
        val newList = mutableListOf<TunisianChartModelEntity>()
        courses.forEach {

            val tunisianChartModelEntity = TunisianChartModelEntity()

            tunisianChartModelEntity.reportDate = it.reportDate
            tunisianChartModelEntity.totalConfirmed = it.totalConfirmed
            tunisianChartModelEntity.totalDeaths = it.totalDeaths
            tunisianChartModelEntity.deltaConfirmed = it.deltaConfirmed
            tunisianChartModelEntity.deltaRecovered = it.deltaRecovered
            tunisianChartModelEntity.totalRecovered = it.totalRecovered

            newList += tunisianChartModelEntity
        }

        return newList
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }

    fun setLastPosition(context : Context, position :Int){
        val preference = context.getSharedPreferences("LastPositionPrefernces",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putInt("LastPosition",position)
        editor.commit()

    }

    fun setLastFilter(context : Context, filter :Int){
        val preference = context.getSharedPreferences("LastPositionPrefernces",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putInt("LastFilter",filter)
        editor.commit()

    }

    fun setFirstLaunch(context : Context, value :Boolean){
        val preference = context.getSharedPreferences("FirstLaunch",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putBoolean("IsFirstLaunch",value)
        editor.commit()

    }

    fun setFirstLaunchDashboard(context : Context, value :Boolean){
        val preference = context.getSharedPreferences("FirstLaunchDashboard",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putBoolean("IsFirstLaunchDashboard",value)
        editor.commit()

    }

    fun setLanguage(context : Context, language: String){
        val preference = context.getSharedPreferences("FirstLaunch",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putString("Language",language)
        editor.commit()

    }

    fun setMode(context : Context, mode: String){
        val preference = context.getSharedPreferences("Mode",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putString("Mode",mode)
        editor.commit()

    }

    fun setDataAvailable(context : Context, available :Boolean){
        val preference = context.getSharedPreferences("DataAvailable",Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putBoolean("IsAvailable",available)
        editor.commit()

    }

    fun getLastPosition(context : Context) : Int {
        val preference = context.getSharedPreferences("LastPositionPrefernces",Context.MODE_PRIVATE)
        return preference.getInt("LastPosition",0)
    }

    fun getLastFilter(context : Context) : Int {
        val preference = context.getSharedPreferences("LastPositionPrefernces",Context.MODE_PRIVATE)
        return preference.getInt("LastFilter",0)
    }

    fun getFirstLaunch(context : Context) : Boolean {
        val preference = context.getSharedPreferences("FirstLaunch",Context.MODE_PRIVATE)
        return preference.getBoolean("IsFirstLaunch",true)
    }

    fun getFirstLaunchDashboard(context : Context) : Boolean {
        val preference = context.getSharedPreferences("FirstLaunchDashboard",Context.MODE_PRIVATE)
        return preference.getBoolean("IsFirstLaunchDashboard",true)
    }

    fun getLanguage(context : Context) : String? {
        val preference = context.getSharedPreferences("FirstLaunch",Context.MODE_PRIVATE)
        return preference.getString("Language","en")
    }

    fun getMode(context : Context) : String? {
        val preference = context.getSharedPreferences("Mode",Context.MODE_PRIVATE)
        return preference.getString("Mode","night")
    }

    fun getDataAvailable(context : Context) : Boolean {
        val preference = context.getSharedPreferences("DataAvailable",Context.MODE_PRIVATE)
        return preference.getBoolean("IsAvailable",false)
    }

    fun applyLanguage(context: Context, language: String): Context {
        val locale = Locale(language)
        val configuration = context.resources.configuration
        val displayMetrics = context.resources.displayMetrics

        Locale.setDefault(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
            context.createConfigurationContext(configuration)
        } else {
            configuration.locale = locale
            context.resources.updateConfiguration(configuration, displayMetrics)
            context
        }
    }


}