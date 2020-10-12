package com.example.covidkotlinproject.di

import com.example.covidkotlinproject.ui.main.viewmodel.CountriesViewModel
import com.example.covidkotlinproject.ui.main.viewmodel.CountryDetailViewModel
import com.example.covidkotlinproject.ui.main.viewmodel.HomeViewModel
import com.example.covidkotlinproject.ui.main.viewmodel.TunisianDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get(), get(),get(),get(),get()) }
    viewModel { CountriesViewModel(get()) }
    viewModel { CountryDetailViewModel(get()) }
    viewModel { TunisianDetailViewModel(get(),get())}
}