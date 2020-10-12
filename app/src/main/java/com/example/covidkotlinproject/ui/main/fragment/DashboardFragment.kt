package com.example.covidkotlinproject.ui.main.fragment

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.api.local.entity.MainModelEntity
import com.example.covidkotlinproject.ui.language.activity.LanguageActivity
import com.example.covidkotlinproject.ui.main.activity.MainActivity
import com.example.covidkotlinproject.ui.main.viewmodel.HomeViewModel
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.color
import com.example.covidkotlinproject.utils.gone
import com.example.covidkotlinproject.utils.visible
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class DashboardFragment : Fragment(){

    companion object {

        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
    }

    private val homeViewModel : HomeViewModel by viewModel()
    private var initMode: String? = "night"
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initMode = NumberUtils.getMode(context!!)

        return if(initMode == "night"){
            inflater.inflate(R.layout.fragment_main, container, false)
        }else{
            inflater.inflate(R.layout.fragment_main_day, container, false)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        firebaseAnalytics.logEvent("Dashboard", null)


        if(NumberUtils.getFirstLaunchDashboard(context!!)){
            if(NumberUtils.isOnline(context!!)){
                initData()
            }else{
                relative_error.visible()
                progressBar.gone()
            }
        }else{
            if(NumberUtils.isOnline(context!!)){
                initData()
            }else{
                if(NumberUtils.getDataAvailable(context!!)){
                    initData()
                }else{
                    relative_error.visible()
                    progressBar.gone()
                }
            }
        }

        NumberUtils.setFirstLaunchDashboard(context!!,false)

        initListCountries()

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity!!.finishAffinity()
        }

        refresh.setOnClickListener {
            if(NumberUtils.isOnline(context!!))
                relative_error.gone()

            initData()
        }

        settings.setOnClickListener {
            val intent = Intent (context, LanguageActivity::class.java)
            context!!.startActivity(intent)
        }

        tunisian_detail.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_navigation_home_to_tunisian_detail)
        }

        all_countries.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_navigation_home_to_countries)
        }
    }

    private fun initListCountries() {
        NumberUtils.setLastPosition(context!!,0)
        NumberUtils.setLastFilter(context!!,0)
    }

    private fun initData() {
        homeViewModel.initNetworkRequest()
        homeViewModel.getCountries()
        homeViewModel.getTunisianDetailByCode("TN")
        homeViewModel.getTunisianDetail()
        homeViewModel.getMainModel().observe(viewLifecycleOwner, Observer {

            if (it != null){
                it.let {
                    val mainModel: MainModelEntity = it
                    initStats(mainModel.total!!,(mainModel.total!!-(mainModel.recovered!! + mainModel.deaths!!)),mainModel.recovered!!,mainModel.deaths!!,mainModel.newCases!!,mainModel.newDeaths!!)
                    progressBar.gone()
                    relative_all.visible()
                    relative_last_update.visible()
                    NumberUtils.setDataAvailable(context!!,true)
                }
            }else {
                progressBar.gone()
                NumberUtils.setDataAvailable(context!!,false)
            }

        })
    }

    private fun initStats(total: Int, active: Int, recovered: Int, deaths: Int, newCases : Int, newDeaths : Int) {
        initChart(active,"Active",recovered,"Recovered",deaths,"Deaths")
        initNumbers(total,active,recovered,deaths)
        initPercents(total,active,recovered,deaths)
        initNewCases(newCases,newDeaths)
        initLastUpdate()
    }

    private fun initLastUpdate() {

        val date = getCurrentDateTime()
        val dateInString = date.toString("dd-MM-yyyy")

        tv_last_update2.setText(resources.getString(R.string.last_update) + " " + dateInString)
    }

    private fun initPercents(i: Int, i1: Int, i2: Int, i3: Int) {

        tv_percent_active.setText(("%.2f".format(i1*100.toDouble()/i)) + " %")
        tv_percent_recovered.setText(("%.2f".format(i2*100.toDouble()/i)) + " %")
        tv_percent_deaths.setText(("%.2f".format(i3*100.toDouble()/i)) + " %")
        tv_closed_percent.setText(("%.2f".format((i2+i3)*100.toDouble()/i)) + " %")

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

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}