package com.example.covidkotlinproject

import android.app.Application
import com.example.covidkotlinproject.di.appComponent
import com.google.android.gms.ads.MobileAds
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
        MobileAds.initialize(this)

    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appComponent)
    }
}