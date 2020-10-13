package com.example.covidkotlinproject.ui.main.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.api.local.entity.CountriesModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianChartModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianModelEntity
import com.example.covidkotlinproject.model.CountryDetailResponse
import com.example.covidkotlinproject.model.ModelChartExemple
import com.example.covidkotlinproject.model.TimelineItems
import com.example.covidkotlinproject.model.TunisianDetailChart
import com.example.covidkotlinproject.ui.main.viewmodel.CountryDetailViewModel
import com.example.covidkotlinproject.ui.main.viewmodel.TunisianDetailViewModel
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.color
import com.example.covidkotlinproject.utils.gone
import com.example.covidkotlinproject.utils.visible
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_tunisian_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TunisianDetailFragment : Fragment(){

    companion object {

        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 75f
        private const val TOTAL_STATE = 0

    }

    private val tunisianDetailViewModel : TunisianDetailViewModel by viewModel()
    private var initMode: String? = "night"
    private var initLanguage: String? = "en"
    private lateinit var firebaseAnalytics: FirebaseAnalytics



    private var currentState = TOTAL_STATE



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initMode = NumberUtils.getMode(context!!)
        initLanguage = NumberUtils.getLanguage(context!!)

        return if(initMode == "night"){
            inflater.inflate(R.layout.fragment_tunisian_detail, container, false)
        }else{
            inflater.inflate(R.layout.fragment_tunisian_detail_day, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
        firebaseAnalytics.logEvent("TunisianDetail", null)

        initTunisianDetail()
        initTunisianChart()
    }

    private fun initTunisianChart() {
        tunisianDetailViewModel.getTunisianDetail().observe(viewLifecycleOwner, Observer {

            it?.let {
                setupChart(it,true)
                setupChart(it,false)
            }
        })
    }

    private fun initTunisianDetail() {
        tunisianDetailViewModel.getTunisianDetailByCode().observe(viewLifecycleOwner, Observer {

            if (it != null){
                it.let {
                    val tunisianModelEntity: TunisianModelEntity = it
                    initStats(tunisianModelEntity.total_cases!!,tunisianModelEntity.total_active_cases!!,
                        tunisianModelEntity.total_recovered!!,tunisianModelEntity.total_deaths!!,
                        tunisianModelEntity.total_new_cases_today!!,tunisianModelEntity.total_new_deaths_today!!,
                        tunisianModelEntity.title!!,tunisianModelEntity.code!!)
                    title.visible()
                    relative_body.visible()
                    progressBar.gone()
                }

            }else {
                progressBar.gone()
                relative_error.visible()
            }
        })
    }

    private fun setupChart(dailyData: List<TunisianChartModelEntity>, isActive : Boolean) {

        if(initLanguage == "ar"){
            active_chart.layoutDirection = View.LAYOUT_DIRECTION_LTR
            deaths_chart.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }

        val daily = dailyData
        with(line_chart) {

            if(initMode == "night"){
                legend.textColor = color(R.color.white)
                xAxis.textColor = color(R.color.grey)
                axisLeft.textColor = color(R.color.grey)
            }else{
                legend.textColor = color(R.color.black)
                xAxis.textColor = color(R.color.black)
                axisLeft.textColor = color(R.color.black)
            }

            animateX(1500)
            xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
            axisRight.isEnabled = false
            description.isEnabled = false
            legend.isEnabled = false

            axisLeft.enableGridDashedLine(10f, 10f, 2f)
            xAxis.enableGridDashedLine(10f, 10f, 2f)

            val dates = daily.map { it.reportDate }
            xAxis.valueFormatter = object : IndexAxisValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return dates[value.toInt()]
                }
            }

        }

        with(line_chart_deaths) {
            animateX(1500)
            xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM

            if(initMode == "night"){
                legend.textColor = color(R.color.white)
                xAxis.textColor = color(R.color.grey)
                axisLeft.textColor = color(R.color.grey)
            }else{
                legend.textColor = color(R.color.black)
                xAxis.textColor = color(R.color.black)
                axisLeft.textColor = color(R.color.black)
            }

            axisRight.isEnabled = false
            description.isEnabled = false
            legend.isEnabled = false

            axisLeft.enableGridDashedLine(10f, 10f, 2f)
            xAxis.enableGridDashedLine(10f, 10f, 2f)

            val dates = daily.map { it.reportDate }
            xAxis.valueFormatter = object : IndexAxisValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return dates[value.toInt()]
                }
            }

        }

        if (isActive){

            val confirmed = LineDataSet(
                daily.mapIndexed { index, dailyItem ->
                    val data = when (currentState) {
                        TOTAL_STATE -> dailyItem.totalConfirmed.toFloat()
                        else -> dailyItem.deltaConfirmed.toFloat()
                    }
                    Entry(
                        index.toFloat(),
                        data,
                        dailyItem.reportDate
                    )
                }, "Confirmed"
            ).apply {
                setLineChartStyle(this, R.color.yellow)
            }

            line_chart.data = LineData(confirmed)
            line_chart.invalidate()


        }else {

            val deaths = LineDataSet(
            daily.mapIndexed { index, dailyItem ->
                val data = when (currentState) {
                    TOTAL_STATE -> dailyItem.totalDeaths.toFloat()
                    else -> dailyItem.deltaRecovered.toFloat()
                }
                Entry(
                    index.toFloat(),
                    data,
                    dailyItem.reportDate
                )
            }, "Deaths"
        ).apply {
            setLineChartStyle(this, R.color.red)
        }

            line_chart_deaths.data = LineData(deaths)
            line_chart_deaths.invalidate()

        }
    }

    private fun setLineChartStyle(lineDataSet: LineDataSet, @ColorRes colorResId: Int) {
        with(lineDataSet) {
            color = color(colorResId)
            lineWidth = 2f
            circleRadius = 1f
            setDrawCircleHole(false)
            setCircleColor(color(colorResId))
            mode = LineDataSet.Mode.CUBIC_BEZIER
            valueTextColor = color(R.color.white)
            setDrawFilled(true)
            fillColor = color(colorResId)
            fillAlpha = 60
        }
    }

    private fun initStats(total: Int, active: Int, recovered: Int, deaths: Int, newCases : Int, newDeaths : Int, title : String, flag : String) {
        initChart(active,"Active",recovered,"Recovered",deaths,"Deaths")
        initNumbers(total,active,recovered,deaths)
        initPercents(total,active,recovered,deaths)
        initNewCases(newCases,newDeaths)
        initTitle(flag)
    }

    private fun initTitle(flag: String) {
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