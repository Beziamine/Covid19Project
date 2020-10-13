package com.example.covidkotlinproject.ui.main.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.model.CountryDetailResponse
import com.example.covidkotlinproject.ui.main.viewmodel.CountryDetailViewModel
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.color
import com.example.covidkotlinproject.utils.gone
import com.example.covidkotlinproject.utils.visible
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_detail_country.*
import kotlinx.android.synthetic.main.fragment_detail_country.country_flag
import kotlinx.android.synthetic.main.fragment_detail_country.country_name
import kotlinx.android.synthetic.main.fragment_detail_country.relative_body
import kotlinx.android.synthetic.main.fragment_detail_country.relative_error
import kotlinx.android.synthetic.main.fragment_detail_country.title
import kotlinx.android.synthetic.main.fragment_detail_country.tv_closed
import kotlinx.android.synthetic.main.fragment_detail_country.tv_state
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.pie_chart
import kotlinx.android.synthetic.main.fragment_main.progressBar
import kotlinx.android.synthetic.main.fragment_main.tv_active
import kotlinx.android.synthetic.main.fragment_main.tv_deaths
import kotlinx.android.synthetic.main.fragment_main.tv_new_cases
import kotlinx.android.synthetic.main.fragment_main.tv_new_deaths
import kotlinx.android.synthetic.main.fragment_main.tv_percent_active
import kotlinx.android.synthetic.main.fragment_main.tv_percent_deaths
import kotlinx.android.synthetic.main.fragment_main.tv_percent_recovered
import kotlinx.android.synthetic.main.fragment_main.tv_recovered
import kotlinx.android.synthetic.main.fragment_main.tv_total
import kotlinx.android.synthetic.main.fragment_tunisian_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryDetailFragment : Fragment(){

    companion object {

        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
    }

    private val countryDetailViewModel : CountryDetailViewModel by viewModel()
    private var initMode: String? = "night"
    private lateinit var firebaseAnalytics: FirebaseAnalytics




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initMode = NumberUtils.getMode(context!!)

        return if(initMode == "night"){
            inflater.inflate(R.layout.fragment_detail_country, container, false)
        }else{
            inflater.inflate(R.layout.fragment_detail_country_day, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        firebaseAnalytics.logEvent("CountryDetail", null)

        val countryCode = checkNotNull(arguments?.getString("countryCode"))

        if(NumberUtils.isOnline(context!!)){
            initCountryDetail(countryCode)
        }else{
            progressBar.gone()
            relative_error.visible()
        }


    }

    private fun initCountryDetail(countryCode : String) {
        countryDetailViewModel.getCountryDetailByCode(countryCode)
        countryDetailViewModel.getCountryDetailLiveData.observe(viewLifecycleOwner, Observer {

            if (it != null){
                it.let {
                    val countryDetailResponse: CountryDetailResponse = it
                    initStats(countryDetailResponse.countrydata[0].total_cases,
                        countryDetailResponse.countrydata[0].total_cases - (countryDetailResponse.countrydata[0].total_recovered + countryDetailResponse.countrydata[0].total_deaths),
//                        countryDetailResponse.countrydata[0].total_active_cases,
                        countryDetailResponse.countrydata[0].total_recovered,countryDetailResponse.countrydata[0].total_deaths,
                        countryDetailResponse.countrydata[0].total_new_cases_today,countryDetailResponse.countrydata[0].total_new_deaths_today,
                        countryDetailResponse.countrydata[0].info.title,countryDetailResponse.countrydata[0].info.code)
                    title.visible()
                    relative_body.visible()
                    progressBar.gone()
                }
            }else {
                progressBar.gone()
            }
        })
    }

    private fun initStats(total: Int, active: Int, recovered: Int, deaths: Int, newCases : Int, newDeaths : Int, title : String, flag : String) {
        initChart(active,"Active",recovered,"Recovered",deaths,"Deaths")
        initNumbers(total,active,recovered,deaths)
        initPercents(total,active,recovered,deaths)
        initNewCases(newCases,newDeaths)
        initTitle(title,flag)
    }

    private fun initTitle(title: String, flag: String) {
        country_name.setText(title)
        Glide.with(context!!)
            .load(NumberUtils.getCountryFlag(flag))
            .placeholder(android.R.color.transparent)
            .centerCrop()
            .into(country_flag)

    }

    private fun initPercents(i: Int, i1: Int, i2: Int, i3: Int) {

        tv_percent_active.setText(("%.2f".format(i1*100.toDouble()/i)).toString() + " %")
        tv_percent_recovered.setText(("%.2f".format(i2*100.toDouble()/i)).toString() + " %")
        tv_percent_deaths.setText(("%.2f".format(i3*100.toDouble()/i)).toString() + " %")
        tv_closed.setText(("%.2f".format((i2+i3)*100.toDouble()/i)).toString() + " %")

        if(((i2+i3)*100/i) >= 90){
            tv_state.setText(getString(R.string.good))
            tv_state.setTextColor(color(R.color.green))
        }else if (((i2+i3)*100/i) < 90 && ((i2+i3)*100/i) >= 50){
            tv_state.setText(getString(R.string.medium))
            tv_state.setTextColor(color(R.color.yellow))
        }else{
            tv_state.setText(getString(R.string.bad))
            tv_state.setTextColor(color(R.color.red))
        }

    }

    private fun initNewCases(newCases: Int, newDeaths: Int) {

        tv_new_cases.setText("+ " + NumberUtils.numberFormat(newCases))
        tv_new_deaths.setText("+ " + NumberUtils.numberFormat(newDeaths))

    }

    private fun initNumbers(i: Int, i1: Int, i2: Int, i3: Int) {

        startNumberChangeAnimator(i, tv_total)
        startNumberChangeAnimator(i1, tv_active)
        startNumberChangeAnimator(i2, tv_recovered)
        startNumberChangeAnimator(i3, tv_deaths)

    }

    private fun initChart(i: Int, s: String, i1: Int, s1: String, i2: Int, s2: String) {

        val pieChart = pie_chart

        val pieDataSet = PieDataSet(
            listOf(
                PieEntry(i.toFloat(), s),
                PieEntry(i1.toFloat(), s1),
                PieEntry(i2.toFloat(), s2)
            ), "Covid19")


        val colors = arrayListOf(
            color(R.color.yellow),
            color(R.color.green),
            color(R.color.red)
        )

        pieDataSet.colors = colors

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(false)
        with(pieChart) {
            if (data == pieData) return
            data = pieData
            legend.isEnabled = false
            description = null
            holeRadius = PIE_RADIUS
            if(initMode == "night"){
                setHoleColor(ContextCompat.getColor(context, R.color.backgroundSecond))
            }else{
                setHoleColor(ContextCompat.getColor(context, R.color.backgroundSecondDay))
            }
            setDrawEntryLabels(false)
            animateY(PIE_ANIMATION_DURATION, com.github.mikephil.charting.animation.Easing.EaseInOutQuart)
            invalidate()
        }

    }

    private fun startNumberChangeAnimator(finalValue: Int?, view: TextView) {
        val initialValue = NumberUtils.extractDigit(view.text.toString())
        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue ?: 0)
        valueAnimator.duration = TEXT_ANIMATION_DURATION
        valueAnimator.addUpdateListener { value ->
            view.text = NumberUtils.numberFormat(value.animatedValue.toString().toInt())
        }
        valueAnimator.start()
    }
}