package com.example.covidkotlinproject.ui.main.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.ui.main.activity.MainActivity
import com.example.covidkotlinproject.ui.main.adapter.CountriesAdapter
import com.example.covidkotlinproject.ui.main.viewmodel.CountriesViewModel
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.gone
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_main.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class CountriesFragment : Fragment(){

    private val countriesViewModel : CountriesViewModel by viewModel()
    private var initMode: String? = "night"
    private var selectedFilter: Int = 0
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initMode = NumberUtils.getMode(context!!)

        return if(initMode == "night"){
            inflater.inflate(R.layout.fragment_countries, container, false)
        }else{
            inflater.inflate(R.layout.fragment_countries_day, container, false)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        firebaseAnalytics.logEvent("ListCountries", null)

        val recyclerView = recycler_countries
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager


        filter_total.setOnClickListener {
            search.setText("")
            getCountries(0)
            switchSelectedBackground(0)
            selectedFilter = 0

        }

        filter_closed.setOnClickListener {
            search.setText("")
            getCountries(1)
            switchSelectedBackground(1)
            selectedFilter = 1

        }

        filter_deaths.setOnClickListener {
            search.setText("")
            getCountries(2)
            switchSelectedBackground(2)
            selectedFilter = 2

        }

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(s.isNotEmpty()){
                    countriesViewModel.getCountries().observe(viewLifecycleOwner, Observer {
                        val countries: List<CountriesModelEntity> = it

                        val countriesSearched = ArrayList<CountriesModelEntity>()

                        countries.forEach {
                            if(it.title!!.toLowerCase(Locale.getDefault()).startsWith(s)){
                                countriesSearched.add(it)
                            }
                        }

                        when (selectedFilter) {
                            0 -> {
                                recycler_countries.adapter = CountriesAdapter(view?.context,countriesSearched.sortedByDescending { it.total_cases!!},0)
                            }
                            1 -> {
                                recycler_countries.adapter = CountriesAdapter(view?.context,countriesSearched.sortedByDescending { it.closed_cases!!},1)
                            }
                            else -> {
                                recycler_countries.adapter = CountriesAdapter(view?.context,countriesSearched.sortedByDescending { it.total_deaths!!},2)

                            }
                        }

                    })
                }

            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun init(){

        getCountries(NumberUtils.getLastFilter(context!!))
        switchSelectedBackground(NumberUtils.getLastFilter(context!!))
        recycler_countries.scrollToPosition(NumberUtils.getLastPosition(context!!))
        search.setText("")
    }

    private fun getCountries(indice : Int) {
        countriesViewModel.getCountries().observe(viewLifecycleOwner, Observer {
            val countries: List<CountriesModelEntity> = it

            when (indice) {
                0 -> recycler_countries.adapter = CountriesAdapter(view?.context,countries.sortedByDescending { it.total_cases },0)
                1 -> recycler_countries.adapter = CountriesAdapter(view?.context,countries.sortedByDescending { it.closed_cases },1)
                else -> recycler_countries.adapter = CountriesAdapter(view?.context,countries.sortedByDescending { it.total_deaths},2)
            }

            if (countries.isNotEmpty())
                progressBar.gone()
        })
    }

    private fun switchSelectedBackground(indice: Int){

        when (indice) {
            0 -> {
                filter_total.setBackgroundResource(R.drawable.layout_relative_grey_selected)
                filter_closed.setBackgroundResource(R.drawable.layout_relative_green)
                filter_deaths.setBackgroundResource(R.drawable.layout_relative_red)
            }
            1 -> {
                filter_total.setBackgroundResource(R.drawable.layout_relative)
                filter_closed.setBackgroundResource(R.drawable.layout_relative_green_selected)
                filter_deaths.setBackgroundResource(R.drawable.layout_relative_red)
            }
            else -> {
                filter_total.setBackgroundResource(R.drawable.layout_relative)
                filter_closed.setBackgroundResource(R.drawable.layout_relative_green)
                filter_deaths.setBackgroundResource(R.drawable.layout_relative_red_selected)
            }
        }
    }

}