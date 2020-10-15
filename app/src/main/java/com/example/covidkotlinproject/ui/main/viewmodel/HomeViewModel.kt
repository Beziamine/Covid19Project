package com.example.covidkotlinproject.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.covidkotlinproject.api.local.dao.CountriesDao
import com.example.covidkotlinproject.api.local.dao.MainDao
import com.example.covidkotlinproject.api.local.dao.TunisianChartDao
import com.example.covidkotlinproject.api.local.dao.TunisianDao
import com.example.covidkotlinproject.api.local.entity.MainModelEntity
import com.example.covidkotlinproject.api.local.entity.TunisianModelEntity
import com.example.covidkotlinproject.api.remote.ApiService
import com.example.covidkotlinproject.model.*
import com.example.covidkotlinproject.utils.NumberUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class HomeViewModel(private val modelDao: MainDao, private val countriesDao: CountriesDao, private val tunisianDao : TunisianDao,private val tunisianChartDao: TunisianChartDao, private val apiService: ApiService) : ViewModel() {


    fun initNetworkRequest(){
        val call = apiService.getMain()
        call.enqueue(object : Callback<MainResponse?> {
            override fun onResponse(
                call: Call<MainResponse?>?,
                response: Response<MainResponse?>?
            ) {
                response?.body()?.let { mainModel: MainResponse ->
                    thread {

                        val mainModel : MainModel = mainModel.results.get(0)
                        val mainModelEntity = MainModelEntity()

                        mainModelEntity.total = mainModel.total_cases
                        mainModelEntity.confirmed = mainModel.total_active_cases
                        mainModelEntity.recovered = mainModel.total_recovered
                        mainModelEntity.deaths = mainModel.total_deaths
                        mainModelEntity.newCases = mainModel.total_new_cases_today
                        mainModelEntity.newDeaths = mainModel.total_new_deaths_today

                        modelDao.delete()
                        modelDao.add(mainModelEntity)
                    }
                }
            }

            override fun onFailure(call: Call<MainResponse?>, t: Throwable?) {
            }
        })

    }

    fun getCountries() {
        val call = apiService.getCountries()
        call.enqueue(object : Callback<CountryResponse?> {
            override fun onResponse(
                call: Call<CountryResponse?>?,
                response: Response<CountryResponse?>?
            ) {
                response?.body()?.let { countries: CountryResponse? ->
                    thread {
                        countriesDao.deleteCountries()

                        val list = listOf(
                            countries!!.countryitems[0].a1!!,
                            countries!!.countryitems[0].a2!!,
                            countries!!.countryitems[0].a3!!,
                            countries!!.countryitems[0].a4!!,
                            countries!!.countryitems[0].a5!!,
                            countries!!.countryitems[0].a6!!,
                            countries!!.countryitems[0].a7!!,
                            countries!!.countryitems[0].a8!!,
                            countries!!.countryitems[0].a9!!,
                            countries!!.countryitems[0].a10!!,
                            countries!!.countryitems[0].a11!!,
                            countries!!.countryitems[0].a12!!,
                            countries!!.countryitems[0].a13!!,
                            countries!!.countryitems[0].a14!!,
                            countries!!.countryitems[0].a15!!,
                            countries!!.countryitems[0].a16!!,
                            countries!!.countryitems[0].a17!!,
                            countries!!.countryitems[0].a18!!,
                            countries!!.countryitems[0].a19!!,
                            countries!!.countryitems[0].a20!!,
                            countries!!.countryitems[0].a21!!,
                            countries!!.countryitems[0].a22!!,
                            countries!!.countryitems[0].a23!!,
                            countries!!.countryitems[0].a24!!,
                            countries!!.countryitems[0].a25!!,
                            countries!!.countryitems[0].a26!!,
                            countries!!.countryitems[0].a27!!,
                            countries!!.countryitems[0].a28!!,
                            countries!!.countryitems[0].a29!!,
                            countries!!.countryitems[0].a30!!,
                            countries!!.countryitems[0].a31!!,
                            countries!!.countryitems[0].a32!!,
                            countries!!.countryitems[0].a33!!,
                            countries!!.countryitems[0].a35!!,
                            countries!!.countryitems[0].a36!!,
                            countries!!.countryitems[0].a37!!,
                            countries!!.countryitems[0].a38!!,
                            countries!!.countryitems[0].a39!!,
                            countries!!.countryitems[0].a40!!,
                            countries!!.countryitems[0].a41!!,
                            countries!!.countryitems[0].a42!!,
                            countries!!.countryitems[0].a43!!,
                            countries!!.countryitems[0].a44!!,
                            countries!!.countryitems[0].a45!!,
                            countries!!.countryitems[0].a46!!,
                            countries!!.countryitems[0].a47!!,
                            countries!!.countryitems[0].a48!!,
                            countries!!.countryitems[0].a49!!,
                            countries!!.countryitems[0].a50!!,
                            countries!!.countryitems[0].a51!!,
                            countries!!.countryitems[0].a52!!,
                            countries!!.countryitems[0].a53!!,
                            countries!!.countryitems[0].a54!!,
                            countries!!.countryitems[0].a55!!,
                            countries!!.countryitems[0].a56!!,
                            countries!!.countryitems[0].a57!!,
                            countries!!.countryitems[0].a58!!,
                            countries!!.countryitems[0].a59!!,
                            countries!!.countryitems[0].a60!!,
                            countries!!.countryitems[0].a61!!,
                            countries!!.countryitems[0].a62!!,
                            countries!!.countryitems[0].a63!!,
                            countries!!.countryitems[0].a64!!,
                            countries!!.countryitems[0].a65!!,
                            countries!!.countryitems[0].a66!!,
                            countries!!.countryitems[0].a67!!,
                            countries!!.countryitems[0].a68!!,
                            countries!!.countryitems[0].a69!!,
                            countries!!.countryitems[0].a70!!,
                            countries!!.countryitems[0].a71!!,
                            countries!!.countryitems[0].a72!!,
                            countries!!.countryitems[0].a73!!,
                            countries!!.countryitems[0].a74!!,
                            countries!!.countryitems[0].a75!!,
                            countries!!.countryitems[0].a77!!,
                            countries!!.countryitems[0].a78!!,
                            countries!!.countryitems[0].a79!!,
                            countries!!.countryitems[0].a80!!,
                            countries!!.countryitems[0].a81!!,
                            countries!!.countryitems[0].a82!!,
                            countries!!.countryitems[0].a83!!,
                            countries!!.countryitems[0].a84!!,
                            countries!!.countryitems[0].a85!!,
                            countries!!.countryitems[0].a86!!,
                            countries!!.countryitems[0].a87!!,
                            countries!!.countryitems[0].a88!!,
                            countries!!.countryitems[0].a89!!,
                            countries!!.countryitems[0].a90!!,
                            countries!!.countryitems[0].a91!!,
                            countries!!.countryitems[0].a92!!,
                            countries!!.countryitems[0].a93!!,
                            countries!!.countryitems[0].a94!!,
                            countries!!.countryitems[0].a95!!,
                            countries!!.countryitems[0].a96!!,
                            countries!!.countryitems[0].a97!!,
                            countries!!.countryitems[0].a98!!,
                            countries!!.countryitems[0].a99!!,
                            countries!!.countryitems[0].a100!!,
                            countries!!.countryitems[0].a101!!,
                            countries!!.countryitems[0].a102!!,
                            countries!!.countryitems[0].a103!!,
                            countries!!.countryitems[0].a104!!,
                            countries!!.countryitems[0].a105!!,
                            countries!!.countryitems[0].a106!!,
                            countries!!.countryitems[0].a107!!,
                            countries!!.countryitems[0].a108!!,
                            countries!!.countryitems[0].a109!!,
                            countries!!.countryitems[0].a110!!,
                            countries!!.countryitems[0].a111!!,
                            countries!!.countryitems[0].a112!!,
                            countries!!.countryitems[0].a113!!,
                            countries!!.countryitems[0].a114!!,
                            countries!!.countryitems[0].a115!!,
                            countries!!.countryitems[0].a116!!,
                            countries!!.countryitems[0].a117!!,
                            countries!!.countryitems[0].a118!!,
                            countries!!.countryitems[0].a119!!,
                            countries!!.countryitems[0].a120!!,
                            countries!!.countryitems[0].a121!!,
                            countries!!.countryitems[0].a122!!,
                            countries!!.countryitems[0].a123!!,
                            countries!!.countryitems[0].a124!!,
                            countries!!.countryitems[0].a125!!,
                            countries!!.countryitems[0].a126!!,
                            countries!!.countryitems[0].a127!!,
                            countries!!.countryitems[0].a128!!,
                            countries!!.countryitems[0].a130!!,
                            countries!!.countryitems[0].a131!!,
                            countries!!.countryitems[0].a132!!,
                            countries!!.countryitems[0].a133!!,
                            countries!!.countryitems[0].a134!!,
                            countries!!.countryitems[0].a135!!,
                            countries!!.countryitems[0].a136!!,
                            countries!!.countryitems[0].a137!!,
                            countries!!.countryitems[0].a138!!,
                            countries!!.countryitems[0].a139!!,
                            countries!!.countryitems[0].a140!!,
                            countries!!.countryitems[0].a141!!,
                            countries!!.countryitems[0].a142!!,
                            countries!!.countryitems[0].a143!!,
                            countries!!.countryitems[0].a144!!,
                            countries!!.countryitems[0].a145!!,
                            countries!!.countryitems[0].a147!!,
                            countries!!.countryitems[0].a148!!,
                            countries!!.countryitems[0].a149!!,
                            countries!!.countryitems[0].a150!!,
                            countries!!.countryitems[0].a151!!,
                            countries!!.countryitems[0].a152!!,
                            countries!!.countryitems[0].a153!!,
                            countries!!.countryitems[0].a154!!,
                            countries!!.countryitems[0].a155!!,
                            countries!!.countryitems[0].a156!!,
                            countries!!.countryitems[0].a157!!,
                            countries!!.countryitems[0].a158!!,
                            countries!!.countryitems[0].a159!!,
                            countries!!.countryitems[0].a160!!,
                            countries!!.countryitems[0].a161!!,
                            countries!!.countryitems[0].a162!!,
                            countries!!.countryitems[0].a163!!,
                            countries!!.countryitems[0].a164!!,
                            countries!!.countryitems[0].a165!!,
                            countries!!.countryitems[0].a166!!,
                            countries!!.countryitems[0].a167!!,
                            countries!!.countryitems[0].a168!!,
                            countries!!.countryitems[0].a169!!,
                            countries!!.countryitems[0].a170!!,
                            countries!!.countryitems[0].a171!!,
                            countries!!.countryitems[0].a172!!,
                            countries!!.countryitems[0].a173!!,
                            countries!!.countryitems[0].a174!!,
                            countries!!.countryitems[0].a175!!,
                            countries!!.countryitems[0].a176!!,
                            countries!!.countryitems[0].a178!!,
                            countries!!.countryitems[0].a179!!,
                            countries!!.countryitems[0].a180!!,
                            countries!!.countryitems[0].a181!!,
                            countries!!.countryitems[0].a182!!

                        )
                        countriesDao.addCountries(NumberUtils.convertList(list))
                    }
                }
            }

            override fun onFailure(call: Call<CountryResponse?>?, t: Throwable?) {
            }
        })
    }

    fun getTunisianDetailByCode(countryCode: String){
        val call = apiService.getCountryDetail(countryCode)
        call.enqueue(object : Callback<CountryDetailResponse> {
            override fun onResponse(
                call: Call<CountryDetailResponse>?,
                response: Response<CountryDetailResponse>
            ) {
                response?.body()?.let { detailCountry : CountryDetailResponse ->
                    thread {

                        val countrydata : Countrydata = detailCountry.countrydata[0]
                        val tunisianModelEntity = TunisianModelEntity()

                        tunisianModelEntity.total_active_cases = (countrydata.total_cases - (countrydata.total_deaths + countrydata.total_recovered))
//                        tunisianModelEntity.total_active_cases = countrydata.total_active_cases
                        tunisianModelEntity.total_cases = countrydata.total_cases
                        tunisianModelEntity.total_deaths = countrydata.total_deaths
                        tunisianModelEntity.total_recovered = countrydata.total_recovered
                        tunisianModelEntity.total_new_cases_today = countrydata.total_new_cases_today
                        tunisianModelEntity.total_new_deaths_today = countrydata.total_new_deaths_today
                        tunisianModelEntity.code = countrydata.info.code
                        tunisianModelEntity.title = countrydata.info.title

                        tunisianDao.delete()
                        tunisianDao.add(tunisianModelEntity)
                    }
                }
            }

            override fun onFailure(call: Call<CountryDetailResponse>, t: Throwable?) {

            }
        })
    }

    fun getTunisianDetail(){
        val call = apiService.getTunisianDetail()
        call.enqueue(object : Callback<TunisianDetailResponse> {
            override fun onResponse(
                call: Call<TunisianDetailResponse>?,
                response: Response<TunisianDetailResponse>
            ) {
                response?.body()?.let { tunisianDetail : TunisianDetailResponse ->
                    thread {

                        val list = mutableListOf<ModelChartExemple>()
                        val modelChartExemple = ModelChartExemple()
                        val modelChartExemple1 = ModelChartExemple()
                        val modelChartExemple2 = ModelChartExemple()
                        val modelChartExemple3 = ModelChartExemple()
                        val modelChartExemple4 = ModelChartExemple()
                        val modelChartExemple5 = ModelChartExemple()
                        val modelChartExemple6 = ModelChartExemple()
                        val modelChartExemple7 = ModelChartExemple()
                        val modelChartExemple8 = ModelChartExemple()
                        val modelChartExemple9 = ModelChartExemple()
                        val modelChartExemple10 = ModelChartExemple()
                        val modelChartExemple11 = ModelChartExemple()
                        val modelChartExemple12 = ModelChartExemple()
                        val modelChartExemple13 = ModelChartExemple()
                        val modelChartExemple14 = ModelChartExemple()
                        val modelChartExemple15 = ModelChartExemple()
                        val modelChartExemple16 = ModelChartExemple()
                        val modelChartExemple17 = ModelChartExemple()
                        val modelChartExemple18 = ModelChartExemple()
                        val modelChartExemple19 = ModelChartExemple()
                        val modelChartExemple20 = ModelChartExemple()
                        val modelChartExemple21 = ModelChartExemple()
                        val modelChartExemple22 = ModelChartExemple()
                        val modelChartExemple23 = ModelChartExemple()
                        val modelChartExemple24 = ModelChartExemple()
                        val modelChartExemple25 = ModelChartExemple()
                        val modelChartExemple26 = ModelChartExemple()
                        val modelChartExemple27 = ModelChartExemple()
                        val modelChartExemple28 = ModelChartExemple()
                        val modelChartExemple29 = ModelChartExemple()
                        val modelChartExemple30 = ModelChartExemple()
                        val modelChartExemple31 = ModelChartExemple()
                        val modelChartExemple32 = ModelChartExemple()
                        val modelChartExemple33 = ModelChartExemple()
                        val modelChartExemple34 = ModelChartExemple()
                        val modelChartExemple35 = ModelChartExemple()
                        val modelChartExemple36 = ModelChartExemple()
                        val modelChartExemple37 = ModelChartExemple()
                        val modelChartExemple38 = ModelChartExemple()
                        val modelChartExemple39 = ModelChartExemple()
                        val modelChartExemple40 = ModelChartExemple()
                        val modelChartExemple41 = ModelChartExemple()
                        val modelChartExemple42 = ModelChartExemple()
                        val modelChartExemple43 = ModelChartExemple()
                        val modelChartExemple44 = ModelChartExemple()
                        val modelChartExemple45 = ModelChartExemple()
                        val modelChartExemple46 = ModelChartExemple()
                        val modelChartExemple47 = ModelChartExemple()
                        val modelChartExemple48 = ModelChartExemple()
                        val modelChartExemple49 = ModelChartExemple()
                        val modelChartExemple50 = ModelChartExemple()
                        val modelChartExemple51 = ModelChartExemple()
                        val modelChartExemple52 = ModelChartExemple()
                        val modelChartExemple53 = ModelChartExemple()
                        val modelChartExemple54 = ModelChartExemple()
                        val modelChartExemple55 = ModelChartExemple()
                        val modelChartExemple56 = ModelChartExemple()
                        val modelChartExemple57 = ModelChartExemple()
                        val modelChartExemple58 = ModelChartExemple()
                        val modelChartExemple59 = ModelChartExemple()
                        val modelChartExemple60 = ModelChartExemple()
                        val modelChartExemple61 = ModelChartExemple()
                        val modelChartExemple62 = ModelChartExemple()
                        val modelChartExemple63 = ModelChartExemple()
                        val modelChartExemple64 = ModelChartExemple()
                        val modelChartExemple65 = ModelChartExemple()
                        val modelChartExemple66 = ModelChartExemple()
                        val modelChartExemple67 = ModelChartExemple()
                        val modelChartExemple68 = ModelChartExemple()
                        val modelChartExemple69 = ModelChartExemple()
                        val modelChartExemple70 = ModelChartExemple()
                        val modelChartExemple71 = ModelChartExemple()
                        val modelChartExemple72 = ModelChartExemple()
                        val modelChartExemple73 = ModelChartExemple()
                        val modelChartExemple74 = ModelChartExemple()
                        val modelChartExemple75 = ModelChartExemple()
                        val modelChartExemple76 = ModelChartExemple()
                        val modelChartExemple77 = ModelChartExemple()
                        val modelChartExemple78 = ModelChartExemple()
                        val modelChartExemple79 = ModelChartExemple()
                        val modelChartExemple80 = ModelChartExemple()
                        val modelChartExemple81 = ModelChartExemple()
                        val modelChartExemple82 = ModelChartExemple()
                        val modelChartExemple83 = ModelChartExemple()
                        val modelChartExemple84 = ModelChartExemple()
                        val modelChartExemple85 = ModelChartExemple()
                        val modelChartExemple86 = ModelChartExemple()
                        val modelChartExemple87 = ModelChartExemple()
                        val modelChartExemple88 = ModelChartExemple()
                        val modelChartExemple89 = ModelChartExemple()
                        val modelChartExemple90 = ModelChartExemple()
                        val modelChartExemple91 = ModelChartExemple()
                        val modelChartExemple92 = ModelChartExemple()
                        val modelChartExemple93 = ModelChartExemple()
                        val modelChartExemple94 = ModelChartExemple()
                        val modelChartExemple95 = ModelChartExemple()
                        val modelChartExemple96 = ModelChartExemple()
                        val modelChartExemple97 = ModelChartExemple()
                        val modelChartExemple98 = ModelChartExemple()
                        val modelChartExemple99 = ModelChartExemple()
                        val modelChartExemple100 = ModelChartExemple()
                        val modelChartExemple101 = ModelChartExemple()
                        val modelChartExemple102 = ModelChartExemple()
                        val modelChartExemple103 = ModelChartExemple()
                        val modelChartExemple104 = ModelChartExemple()
                        val modelChartExemple105 = ModelChartExemple()
                        val modelChartExemple106 = ModelChartExemple()
                        val modelChartExemple107 = ModelChartExemple()
                        val modelChartExemple108 = ModelChartExemple()
                        val modelChartExemple109 = ModelChartExemple()
                        val modelChartExemple110 = ModelChartExemple()
                        val modelChartExemple111 = ModelChartExemple()
                        val modelChartExemple112 = ModelChartExemple()
                        val modelChartExemple113 = ModelChartExemple()
                        val modelChartExemple114 = ModelChartExemple()
                        val modelChartExemple115 = ModelChartExemple()
                        val modelChartExemple116 = ModelChartExemple()
                        val modelChartExemple117 = ModelChartExemple()
                        val modelChartExemple118 = ModelChartExemple()
                        val modelChartExemple119 = ModelChartExemple()
                        val modelChartExemple120 = ModelChartExemple()
                        val modelChartExemple121 = ModelChartExemple()
                        val modelChartExemple122 = ModelChartExemple()
                        val modelChartExemple123 = ModelChartExemple()
                        val modelChartExemple124 = ModelChartExemple()
                        val modelChartExemple125 = ModelChartExemple()
                        val modelChartExemple126 = ModelChartExemple()
                        val modelChartExemple127 = ModelChartExemple()
                        val modelChartExemple128 = ModelChartExemple()
                        val modelChartExemple129 = ModelChartExemple()
                        val modelChartExemple130 = ModelChartExemple()
                        val modelChartExemple131 = ModelChartExemple()
                        val modelChartExemple132 = ModelChartExemple()
                        val modelChartExemple133 = ModelChartExemple()
                        val modelChartExemple134 = ModelChartExemple()
                        val modelChartExemple135 = ModelChartExemple()
                        val modelChartExemple136 = ModelChartExemple()
                        val modelChartExemple137 = ModelChartExemple()
                        val modelChartExemple138 = ModelChartExemple()
                        val modelChartExemple139 = ModelChartExemple()
                        val modelChartExemple140 = ModelChartExemple()
                        val modelChartExemple141 = ModelChartExemple()
                        val modelChartExemple142 = ModelChartExemple()
                        val modelChartExemple143 = ModelChartExemple()
                        val modelChartExemple144 = ModelChartExemple()
                        val modelChartExemple145 = ModelChartExemple()
                        val modelChartExemple146 = ModelChartExemple()
                        val modelChartExemple147 = ModelChartExemple()
                        val modelChartExemple148 = ModelChartExemple()
                        val modelChartExemple149 = ModelChartExemple()
                        val modelChartExemple150 = ModelChartExemple()
                        val modelChartExemple151 = ModelChartExemple()
                        val modelChartExemple152 = ModelChartExemple()
                        val modelChartExemple153 = ModelChartExemple()
                        val modelChartExemple154 = ModelChartExemple()
                        val modelChartExemple155 = ModelChartExemple()
                        val modelChartExemple156 = ModelChartExemple()
                        val modelChartExemple157 = ModelChartExemple()
                        val modelChartExemple158 = ModelChartExemple()
                        val modelChartExemple159 = ModelChartExemple()
                        val modelChartExemple160 = ModelChartExemple()
                        val modelChartExemple161 = ModelChartExemple()
                        val modelChartExemple162 = ModelChartExemple()
                        val modelChartExemple163 = ModelChartExemple()
                        val modelChartExemple164 = ModelChartExemple()
                        val modelChartExemple165 = ModelChartExemple()
                        val modelChartExemple166 = ModelChartExemple()
                        val modelChartExemple167 = ModelChartExemple()
                        val modelChartExemple168 = ModelChartExemple()
                        val modelChartExemple169 = ModelChartExemple()
                        val modelChartExemple170 = ModelChartExemple()
                        val modelChartExemple171 = ModelChartExemple()
                        val modelChartExemple172 = ModelChartExemple()
                        val modelChartExemple173 = ModelChartExemple()
                        val modelChartExemple174 = ModelChartExemple()
                        val modelChartExemple175 = ModelChartExemple()
                        val modelChartExemple176 = ModelChartExemple()
                        val modelChartExemple177 = ModelChartExemple()
                        val modelChartExemple178 = ModelChartExemple()
                        val modelChartExemple179 = ModelChartExemple()
                        val modelChartExemple180 = ModelChartExemple()
                        val modelChartExemple181 = ModelChartExemple()
                        val modelChartExemple182 = ModelChartExemple()
                        val modelChartExemple183 = ModelChartExemple()
                        val modelChartExemple184 = ModelChartExemple()
                        val modelChartExemple185 = ModelChartExemple()
                        val modelChartExemple186 = ModelChartExemple()
                        val modelChartExemple187 = ModelChartExemple()
                        val modelChartExemple188 = ModelChartExemple()
                        val modelChartExemple189 = ModelChartExemple()
                        val modelChartExemple190 = ModelChartExemple()
                        val modelChartExemple191 = ModelChartExemple()
                        val modelChartExemple192 = ModelChartExemple()
                        val modelChartExemple193 = ModelChartExemple()
                        val modelChartExemple194 = ModelChartExemple()
                        val modelChartExemple195 = ModelChartExemple()
                        val modelChartExemple196 = ModelChartExemple()
                        val modelChartExemple197 = ModelChartExemple()
                        val modelChartExemple198 = ModelChartExemple()
                        val modelChartExemple199 = ModelChartExemple()
                        val modelChartExemple200 = ModelChartExemple()
                        val modelChartExemple201 = ModelChartExemple()
                        val modelChartExemple202 = ModelChartExemple()
                        val modelChartExemple203 = ModelChartExemple()
                        val modelChartExemple204 = ModelChartExemple()
                        val modelChartExemple205 = ModelChartExemple()
                        val modelChartExemple206 = ModelChartExemple()
                        val modelChartExemple207 = ModelChartExemple()
                        val modelChartExemple208 = ModelChartExemple()
                        val modelChartExemple209 = ModelChartExemple()
                        val modelChartExemple210 = ModelChartExemple()
                        val modelChartExemple211 = ModelChartExemple()
                        val modelChartExemple212 = ModelChartExemple()
                        val modelChartExemple213 = ModelChartExemple()
                        val modelChartExemple214 = ModelChartExemple()
                        val modelChartExemple215 = ModelChartExemple()
                        val modelChartExemple216 = ModelChartExemple()
                        val modelChartExemple217 = ModelChartExemple()
                        val modelChartExemple218 = ModelChartExemple()
                        val modelChartExemple219 = ModelChartExemple()
                        val modelChartExemple220 = ModelChartExemple()
                        val modelChartExemple221 = ModelChartExemple()
                        val modelChartExemple222 = ModelChartExemple()
                        val modelChartExemple223 = ModelChartExemple()
                        val modelChartExemple224 = ModelChartExemple()

                        modelChartExemple.reportDate = "04/03/2020"
                        modelChartExemple.totalConfirmed = tunisianDetail!!.timelineitems[0].a1!!.totalCases
                        modelChartExemple.totalDeaths = tunisianDetail!!.timelineitems[0].a1!!.totalDeaths
                        list.add(modelChartExemple)

                        modelChartExemple1.reportDate = "05/03/2020"
                        modelChartExemple1.totalConfirmed = tunisianDetail!!.timelineitems[0].a2!!.totalCases
                        modelChartExemple1.totalDeaths = tunisianDetail!!.timelineitems[0].a2!!.totalDeaths
                        list.add(modelChartExemple1)

                        modelChartExemple2.reportDate = "06/03/2020"
                        modelChartExemple2.totalConfirmed = tunisianDetail!!.timelineitems[0].a3!!.totalCases
                        modelChartExemple2.totalDeaths = tunisianDetail!!.timelineitems[0].a3!!.totalDeaths
                        list.add(modelChartExemple2)

                        modelChartExemple3.reportDate = "07/03/2020"
                        modelChartExemple3.totalConfirmed = tunisianDetail!!.timelineitems[0].a4!!.totalCases
                        modelChartExemple3.totalDeaths = tunisianDetail!!.timelineitems[0].a4!!.totalDeaths
                        list.add(modelChartExemple3)

                        modelChartExemple4.reportDate = "08/03/2020"
                        modelChartExemple4.totalConfirmed = tunisianDetail!!.timelineitems[0].a5!!.totalCases
                        modelChartExemple4.totalDeaths = tunisianDetail!!.timelineitems[0].a5!!.totalDeaths
                        list.add(modelChartExemple4)

                        modelChartExemple5.reportDate = "09/03/2020"
                        modelChartExemple5.totalConfirmed = tunisianDetail!!.timelineitems[0].a6!!.totalCases
                        modelChartExemple5.totalDeaths = tunisianDetail!!.timelineitems[0].a6!!.totalDeaths
                        list.add(modelChartExemple5)

                        modelChartExemple6.reportDate = "10/03/2020"
                        modelChartExemple6.totalConfirmed = tunisianDetail!!.timelineitems[0].a7!!.totalCases
                        modelChartExemple6.totalDeaths = tunisianDetail!!.timelineitems[0].a7!!.totalDeaths
                        list.add(modelChartExemple6)

                        modelChartExemple7.reportDate = "11/03/2020"
                        modelChartExemple7.totalConfirmed = tunisianDetail!!.timelineitems[0].a8!!.totalCases
                        modelChartExemple7.totalDeaths = tunisianDetail!!.timelineitems[0].a8!!.totalDeaths
                        list.add(modelChartExemple7)

                        modelChartExemple8.reportDate = "12/03/2020"
                        modelChartExemple8.totalConfirmed = tunisianDetail!!.timelineitems[0].a9!!.totalCases
                        modelChartExemple8.totalDeaths = tunisianDetail!!.timelineitems[0].a9!!.totalDeaths
                        list.add(modelChartExemple8)

                        modelChartExemple9.reportDate = "13/03/2020"
                        modelChartExemple9.totalConfirmed = tunisianDetail!!.timelineitems[0].a10!!.totalCases
                        modelChartExemple9.totalDeaths = tunisianDetail!!.timelineitems[0].a10!!.totalDeaths
                        list.add(modelChartExemple9)

                        modelChartExemple10.reportDate = "14/03/2020"
                        modelChartExemple10.totalConfirmed = tunisianDetail!!.timelineitems[0].a11!!.totalCases
                        modelChartExemple10.totalDeaths = tunisianDetail!!.timelineitems[0].a11!!.totalDeaths
                        list.add(modelChartExemple10)

                        modelChartExemple11.reportDate = "15/03/2020"
                        modelChartExemple11.totalConfirmed = tunisianDetail!!.timelineitems[0].a12!!.totalCases
                        modelChartExemple11.totalDeaths = tunisianDetail!!.timelineitems[0].a12!!.totalDeaths
                        list.add(modelChartExemple11)

                        modelChartExemple12.reportDate = "16/03/2020"
                        modelChartExemple12.totalConfirmed = tunisianDetail!!.timelineitems[0].a13!!.totalCases
                        modelChartExemple12.totalDeaths = tunisianDetail!!.timelineitems[0].a13!!.totalDeaths
                        list.add(modelChartExemple12)

                        modelChartExemple13.reportDate = "17/03/2020"
                        modelChartExemple13.totalConfirmed = tunisianDetail!!.timelineitems[0].a14!!.totalCases
                        modelChartExemple13.totalDeaths = tunisianDetail!!.timelineitems[0].a14!!.totalDeaths
                        list.add(modelChartExemple13)

                        modelChartExemple14.reportDate = "18/03/2020"
                        modelChartExemple14.totalConfirmed = tunisianDetail!!.timelineitems[0].a15!!.totalCases
                        modelChartExemple14.totalDeaths = tunisianDetail!!.timelineitems[0].a15!!.totalDeaths
                        list.add(modelChartExemple14)

                        modelChartExemple15.reportDate = "19/03/2020"
                        modelChartExemple15.totalConfirmed = tunisianDetail!!.timelineitems[0].a16!!.totalCases
                        modelChartExemple15.totalDeaths = tunisianDetail!!.timelineitems[0].a16!!.totalDeaths
                        list.add(modelChartExemple15)

                        modelChartExemple16.reportDate = "20/03/2020"
                        modelChartExemple16.totalConfirmed = tunisianDetail!!.timelineitems[0].a17!!.totalCases
                        modelChartExemple16.totalDeaths = tunisianDetail!!.timelineitems[0].a17!!.totalDeaths
                        list.add(modelChartExemple16)

                        modelChartExemple17.reportDate = "21/03/2020"
                        modelChartExemple17.totalConfirmed = tunisianDetail!!.timelineitems[0].a18!!.totalCases
                        modelChartExemple17.totalDeaths = tunisianDetail!!.timelineitems[0].a18!!.totalDeaths
                        list.add(modelChartExemple17)

                        modelChartExemple18.reportDate = "22/03/2020"
                        modelChartExemple18.totalConfirmed = tunisianDetail!!.timelineitems[0].a19!!.totalCases
                        modelChartExemple18.totalDeaths = tunisianDetail!!.timelineitems[0].a19!!.totalDeaths
                        list.add(modelChartExemple18)

                        modelChartExemple19.reportDate = "23/03/2020"
                        modelChartExemple19.totalConfirmed = tunisianDetail!!.timelineitems[0].a20!!.totalCases
                        modelChartExemple19.totalDeaths = tunisianDetail!!.timelineitems[0].a20!!.totalDeaths
                        list.add(modelChartExemple19)

                        modelChartExemple20.reportDate = "24/03/2020"
                        modelChartExemple20.totalConfirmed = tunisianDetail!!.timelineitems[0].a21!!.totalCases
                        modelChartExemple20.totalDeaths = tunisianDetail!!.timelineitems[0].a21!!.totalDeaths
                        list.add(modelChartExemple20)

                        modelChartExemple21.reportDate = "25/03/2020"
                        modelChartExemple21.totalConfirmed = tunisianDetail!!.timelineitems[0].a22!!.totalCases
                        modelChartExemple21.totalDeaths = tunisianDetail!!.timelineitems[0].a22!!.totalDeaths
                        list.add(modelChartExemple21)

                        modelChartExemple22.reportDate = "26/03/2020"
                        modelChartExemple22.totalConfirmed = tunisianDetail!!.timelineitems[0].a23!!.totalCases
                        modelChartExemple22.totalDeaths = tunisianDetail!!.timelineitems[0].a23!!.totalDeaths
                        list.add(modelChartExemple22)

                        modelChartExemple23.reportDate = "27/03/2020"
                        modelChartExemple23.totalConfirmed = tunisianDetail!!.timelineitems[0].a24!!.totalCases
                        modelChartExemple23.totalDeaths = tunisianDetail!!.timelineitems[0].a24!!.totalDeaths
                        list.add(modelChartExemple23)

                        modelChartExemple24.reportDate = "28/03/2020"
                        modelChartExemple24.totalConfirmed = tunisianDetail!!.timelineitems[0].a25!!.totalCases
                        modelChartExemple24.totalDeaths = tunisianDetail!!.timelineitems[0].a25!!.totalDeaths
                        list.add(modelChartExemple24)

                        modelChartExemple25.reportDate = "29/03/2020"
                        modelChartExemple25.totalConfirmed = tunisianDetail!!.timelineitems[0].a26!!.totalCases
                        modelChartExemple25.totalDeaths = tunisianDetail!!.timelineitems[0].a26!!.totalDeaths
                        list.add(modelChartExemple25)

                        modelChartExemple26.reportDate = "30/03/2020"
                        modelChartExemple26.totalConfirmed = tunisianDetail!!.timelineitems[0].a27!!.totalCases
                        modelChartExemple26.totalDeaths = tunisianDetail!!.timelineitems[0].a27!!.totalDeaths
                        list.add(modelChartExemple26)

                        modelChartExemple27.reportDate = "31/03/2020"
                        modelChartExemple27.totalConfirmed = tunisianDetail!!.timelineitems[0].a28!!.totalCases
                        modelChartExemple27.totalDeaths = tunisianDetail!!.timelineitems[0].a28!!.totalDeaths
                        list.add(modelChartExemple27)

                        modelChartExemple28.reportDate = "01/04/2020"
                        modelChartExemple28.totalConfirmed = tunisianDetail!!.timelineitems[0].a29!!.totalCases
                        modelChartExemple28.totalDeaths = tunisianDetail!!.timelineitems[0].a29!!.totalDeaths
                        list.add(modelChartExemple28)

                        modelChartExemple29.reportDate = "02/04/2020"
                        modelChartExemple29.totalConfirmed = tunisianDetail!!.timelineitems[0].a30!!.totalCases
                        modelChartExemple29.totalDeaths = tunisianDetail!!.timelineitems[0].a30!!.totalDeaths
                        list.add(modelChartExemple29)

                        modelChartExemple30.reportDate = "03/04/2020"
                        modelChartExemple30.totalConfirmed = tunisianDetail!!.timelineitems[0].a31!!.totalCases
                        modelChartExemple30.totalDeaths = tunisianDetail!!.timelineitems[0].a31!!.totalDeaths
                        list.add(modelChartExemple30)

                        modelChartExemple31.reportDate = "04/04/2020"
                        modelChartExemple31.totalConfirmed = tunisianDetail!!.timelineitems[0].a32!!.totalCases
                        modelChartExemple31.totalDeaths = tunisianDetail!!.timelineitems[0].a32!!.totalDeaths
                        list.add(modelChartExemple31)

                        modelChartExemple32.reportDate = "05/04/2020"
                        modelChartExemple32.totalConfirmed = tunisianDetail!!.timelineitems[0].a33!!.totalCases
                        modelChartExemple32.totalDeaths = tunisianDetail!!.timelineitems[0].a33!!.totalDeaths
                        list.add(modelChartExemple32)

                        modelChartExemple33.reportDate = "06/04/2020"
                        modelChartExemple33.totalConfirmed = tunisianDetail!!.timelineitems[0].a34!!.totalCases
                        modelChartExemple33.totalDeaths = tunisianDetail!!.timelineitems[0].a34!!.totalDeaths
                        list.add(modelChartExemple33)

                        modelChartExemple34.reportDate = "07/04/2020"
                        modelChartExemple34.totalConfirmed = tunisianDetail!!.timelineitems[0].a35!!.totalCases
                        modelChartExemple34.totalDeaths = tunisianDetail!!.timelineitems[0].a35!!.totalDeaths
                        list.add(modelChartExemple34)

                        modelChartExemple35.reportDate = "08/04/2020"
                        modelChartExemple35.totalConfirmed = tunisianDetail!!.timelineitems[0].a36!!.totalCases
                        modelChartExemple35.totalDeaths = tunisianDetail!!.timelineitems[0].a36!!.totalDeaths
                        list.add(modelChartExemple35)

                        modelChartExemple36.reportDate = "09/04/2020"
                        modelChartExemple36.totalConfirmed = tunisianDetail!!.timelineitems[0].a37!!.totalCases
                        modelChartExemple36.totalDeaths = tunisianDetail!!.timelineitems[0].a37!!.totalDeaths
                        list.add(modelChartExemple36)

                        modelChartExemple37.reportDate = "10/04/2020"
                        modelChartExemple37.totalConfirmed = tunisianDetail!!.timelineitems[0].a38!!.totalCases
                        modelChartExemple37.totalDeaths = tunisianDetail!!.timelineitems[0].a38!!.totalDeaths
                        list.add(modelChartExemple37)

                        modelChartExemple38.reportDate = "11/04/2020"
                        modelChartExemple38.totalConfirmed = tunisianDetail!!.timelineitems[0].a39!!.totalCases
                        modelChartExemple38.totalDeaths = tunisianDetail!!.timelineitems[0].a39!!.totalDeaths
                        list.add(modelChartExemple38)

                        modelChartExemple39.reportDate = "12/04/2020"
                        modelChartExemple39.totalConfirmed = tunisianDetail!!.timelineitems[0].a40!!.totalCases
                        modelChartExemple39.totalDeaths = tunisianDetail!!.timelineitems[0].a40!!.totalDeaths
                        list.add(modelChartExemple39)

                        modelChartExemple40.reportDate = "13/04/2020"
                        modelChartExemple40.totalConfirmed = tunisianDetail!!.timelineitems[0].a41!!.totalCases
                        modelChartExemple40.totalDeaths = tunisianDetail!!.timelineitems[0].a41!!.totalDeaths
                        list.add(modelChartExemple40)

                        modelChartExemple41.reportDate = "14/04/2020"
                        modelChartExemple41.totalConfirmed = tunisianDetail!!.timelineitems[0].a42!!.totalCases
                        modelChartExemple41.totalDeaths = tunisianDetail!!.timelineitems[0].a42!!.totalDeaths
                        list.add(modelChartExemple41)

                        modelChartExemple42.reportDate = "15/04/2020"
                        modelChartExemple42.totalConfirmed = tunisianDetail!!.timelineitems[0].a43!!.totalCases
                        modelChartExemple42.totalDeaths = tunisianDetail!!.timelineitems[0].a43!!.totalDeaths
                        list.add(modelChartExemple42)

                        modelChartExemple43.reportDate = "16/04/2020"
                        modelChartExemple43.totalConfirmed = tunisianDetail!!.timelineitems[0].a44!!.totalCases
                        modelChartExemple43.totalDeaths = tunisianDetail!!.timelineitems[0].a44!!.totalDeaths
                        list.add(modelChartExemple43)

                        modelChartExemple44.reportDate = "17/04/2020"
                        modelChartExemple44.totalConfirmed = tunisianDetail!!.timelineitems[0].a45!!.totalCases
                        modelChartExemple44.totalDeaths = tunisianDetail!!.timelineitems[0].a45!!.totalDeaths
                        list.add(modelChartExemple44)

                        modelChartExemple45.reportDate = "18/04/2020"
                        modelChartExemple45.totalConfirmed = tunisianDetail!!.timelineitems[0].a46!!.totalCases
                        modelChartExemple45.totalDeaths = tunisianDetail!!.timelineitems[0].a46!!.totalDeaths
                        list.add(modelChartExemple45)

                        modelChartExemple46.reportDate = "19/04/2020"
                        modelChartExemple46.totalConfirmed = tunisianDetail!!.timelineitems[0].a47!!.totalCases
                        modelChartExemple46.totalDeaths = tunisianDetail!!.timelineitems[0].a47!!.totalDeaths
                        list.add(modelChartExemple46)

                        modelChartExemple47.reportDate = "20/04/2020"
                        modelChartExemple47.totalConfirmed = tunisianDetail!!.timelineitems[0].a48!!.totalCases
                        modelChartExemple47.totalDeaths = tunisianDetail!!.timelineitems[0].a48!!.totalDeaths
                        list.add(modelChartExemple47)

                        modelChartExemple48.reportDate = "21/04/2020"
                        modelChartExemple48.totalConfirmed = tunisianDetail!!.timelineitems[0].a49!!.totalCases
                        modelChartExemple48.totalDeaths = tunisianDetail!!.timelineitems[0].a49!!.totalDeaths
                        list.add(modelChartExemple48)

                        modelChartExemple49.reportDate = "22/04/2020"
                        modelChartExemple49.totalConfirmed = tunisianDetail!!.timelineitems[0].a50!!.totalCases
                        modelChartExemple49.totalDeaths = tunisianDetail!!.timelineitems[0].a50!!.totalDeaths
                        list.add(modelChartExemple49)

                        modelChartExemple50.reportDate = "23/04/2020"
                        modelChartExemple50.totalConfirmed = tunisianDetail!!.timelineitems[0].a51!!.totalCases
                        modelChartExemple50.totalDeaths = tunisianDetail!!.timelineitems[0].a51!!.totalDeaths
                        list.add(modelChartExemple50)

                        modelChartExemple51.reportDate = "24/04/2020"
                        modelChartExemple51.totalConfirmed = tunisianDetail!!.timelineitems[0].a52!!.totalCases
                        modelChartExemple51.totalDeaths = tunisianDetail!!.timelineitems[0].a52!!.totalDeaths
                        list.add(modelChartExemple51)

                        modelChartExemple52.reportDate = "25/04/2020"
                        modelChartExemple52.totalConfirmed = tunisianDetail!!.timelineitems[0].a53!!.totalCases
                        modelChartExemple52.totalDeaths = tunisianDetail!!.timelineitems[0].a53!!.totalDeaths
                        list.add(modelChartExemple52)

                        modelChartExemple53.reportDate = "26/04/2020"
                        modelChartExemple53.totalConfirmed = tunisianDetail!!.timelineitems[0].a54!!.totalCases
                        modelChartExemple53.totalDeaths = tunisianDetail!!.timelineitems[0].a54!!.totalDeaths
                        list.add(modelChartExemple53)

                        modelChartExemple54.reportDate = "27/04/2020"
                        modelChartExemple54.totalConfirmed = tunisianDetail!!.timelineitems[0].a55!!.totalCases
                        modelChartExemple54.totalDeaths = tunisianDetail!!.timelineitems[0].a55!!.totalDeaths
                        list.add(modelChartExemple54)

                        modelChartExemple55.reportDate = "28/04/2020"
                        modelChartExemple55.totalConfirmed = tunisianDetail!!.timelineitems[0].a56!!.totalCases
                        modelChartExemple55.totalDeaths = tunisianDetail!!.timelineitems[0].a56!!.totalDeaths
                        list.add(modelChartExemple55)

                        modelChartExemple56.reportDate = "29/04/2020"
                        modelChartExemple56.totalConfirmed = tunisianDetail!!.timelineitems[0].a57!!.totalCases
                        modelChartExemple56.totalDeaths = tunisianDetail!!.timelineitems[0].a57!!.totalDeaths
                        list.add(modelChartExemple56)

                        modelChartExemple57.reportDate = "30/04/2020"
                        modelChartExemple57.totalConfirmed = tunisianDetail!!.timelineitems[0].a58!!.totalCases
                        modelChartExemple57.totalDeaths = tunisianDetail!!.timelineitems[0].a58!!.totalDeaths
                        list.add(modelChartExemple57)

                        modelChartExemple58.reportDate = "01/05/2020"
                        modelChartExemple58.totalConfirmed = tunisianDetail!!.timelineitems[0].a59!!.totalCases
                        modelChartExemple58.totalDeaths = tunisianDetail!!.timelineitems[0].a59!!.totalDeaths
                        list.add(modelChartExemple58)

                        modelChartExemple59.reportDate = "02/05/2020"
                        modelChartExemple59.totalConfirmed = tunisianDetail!!.timelineitems[0].a60!!.totalCases
                        modelChartExemple59.totalDeaths = tunisianDetail!!.timelineitems[0].a60!!.totalDeaths
                        list.add(modelChartExemple59)

                        modelChartExemple60.reportDate = "03/05/2020"
                        modelChartExemple60.totalConfirmed = tunisianDetail!!.timelineitems[0].a61!!.totalCases
                        modelChartExemple60.totalDeaths = tunisianDetail!!.timelineitems[0].a61!!.totalDeaths
                        list.add(modelChartExemple60)

                        modelChartExemple61.reportDate = "04/05/2020"
                        modelChartExemple61.totalConfirmed = tunisianDetail!!.timelineitems[0].a62!!.totalCases
                        modelChartExemple61.totalDeaths = tunisianDetail!!.timelineitems[0].a62!!.totalDeaths
                        list.add(modelChartExemple61)

                        modelChartExemple62.reportDate = "05/05/2020"
                        modelChartExemple62.totalConfirmed = tunisianDetail!!.timelineitems[0].a63!!.totalCases
                        modelChartExemple62.totalDeaths = tunisianDetail!!.timelineitems[0].a63!!.totalDeaths
                        list.add(modelChartExemple62)

                        modelChartExemple63.reportDate = "06/05/2020"
                        modelChartExemple63.totalConfirmed = tunisianDetail!!.timelineitems[0].a64!!.totalCases
                        modelChartExemple63.totalDeaths = tunisianDetail!!.timelineitems[0].a64!!.totalDeaths
                        list.add(modelChartExemple63)

                        modelChartExemple64.reportDate = "07/05/2020"
                        modelChartExemple64.totalConfirmed = tunisianDetail!!.timelineitems[0].a65!!.totalCases
                        modelChartExemple64.totalDeaths = tunisianDetail!!.timelineitems[0].a65!!.totalDeaths
                        list.add(modelChartExemple64)

                        modelChartExemple65.reportDate = "08/05/2020"
                        modelChartExemple65.totalConfirmed = tunisianDetail!!.timelineitems[0].a66!!.totalCases
                        modelChartExemple65.totalDeaths = tunisianDetail!!.timelineitems[0].a66!!.totalDeaths
                        list.add(modelChartExemple65)

                        modelChartExemple66.reportDate = "09/05/2020"
                        modelChartExemple66.totalConfirmed = tunisianDetail!!.timelineitems[0].a67!!.totalCases
                        modelChartExemple66.totalDeaths = tunisianDetail!!.timelineitems[0].a67!!.totalDeaths
                        list.add(modelChartExemple66)

                        modelChartExemple67.reportDate = "10/05/2020"
                        modelChartExemple67.totalConfirmed = tunisianDetail!!.timelineitems[0].a68!!.totalCases
                        modelChartExemple67.totalDeaths = tunisianDetail!!.timelineitems[0].a68!!.totalDeaths
                        list.add(modelChartExemple67)

                        modelChartExemple68.reportDate = "11/05/2020"
                        modelChartExemple68.totalConfirmed = tunisianDetail!!.timelineitems[0].a69!!.totalCases
                        modelChartExemple68.totalDeaths = tunisianDetail!!.timelineitems[0].a69!!.totalDeaths
                        list.add(modelChartExemple68)

                        modelChartExemple69.reportDate = "12/05/2020"
                        modelChartExemple69.totalConfirmed = tunisianDetail!!.timelineitems[0].a70!!.totalCases
                        modelChartExemple69.totalDeaths = tunisianDetail!!.timelineitems[0].a70!!.totalDeaths
                        list.add(modelChartExemple69)

                        modelChartExemple70.reportDate = "13/05/2020"
                        modelChartExemple70.totalConfirmed = tunisianDetail!!.timelineitems[0].a71!!.totalCases
                        modelChartExemple70.totalDeaths = tunisianDetail!!.timelineitems[0].a71!!.totalDeaths
                        list.add(modelChartExemple70)

                        modelChartExemple71.reportDate = "14/05/2020"
                        modelChartExemple71.totalConfirmed = tunisianDetail!!.timelineitems[0].a72!!.totalCases
                        modelChartExemple71.totalDeaths = tunisianDetail!!.timelineitems[0].a72!!.totalDeaths
                        list.add(modelChartExemple71)

                        modelChartExemple72.reportDate = "15/05/2020"
                        modelChartExemple72.totalConfirmed = tunisianDetail!!.timelineitems[0].a73!!.totalCases
                        modelChartExemple72.totalDeaths = tunisianDetail!!.timelineitems[0].a73!!.totalDeaths
                        list.add(modelChartExemple72)

                        modelChartExemple73.reportDate = "16/05/2020"
                        modelChartExemple73.totalConfirmed = tunisianDetail!!.timelineitems[0].a74!!.totalCases
                        modelChartExemple73.totalDeaths = tunisianDetail!!.timelineitems[0].a74!!.totalDeaths
                        list.add(modelChartExemple73)

                        modelChartExemple74.reportDate = "17/05/2020"
                        modelChartExemple74.totalConfirmed = tunisianDetail!!.timelineitems[0].a75!!.totalCases
                        modelChartExemple74.totalDeaths = tunisianDetail!!.timelineitems[0].a75!!.totalDeaths
                        list.add(modelChartExemple74)

                        modelChartExemple75.reportDate = "18/05/2020"
                        modelChartExemple75.totalConfirmed = tunisianDetail!!.timelineitems[0].a76!!.totalCases
                        modelChartExemple75.totalDeaths = tunisianDetail!!.timelineitems[0].a76!!.totalDeaths
                        list.add(modelChartExemple75)

                        modelChartExemple76.reportDate = "19/05/2020"
                        modelChartExemple76.totalConfirmed = tunisianDetail!!.timelineitems[0].a77!!.totalCases
                        modelChartExemple76.totalDeaths = tunisianDetail!!.timelineitems[0].a77!!.totalDeaths
                        list.add(modelChartExemple76)

                        modelChartExemple77.reportDate = "20/05/2020"
                        modelChartExemple77.totalConfirmed = tunisianDetail!!.timelineitems[0].a78!!.totalCases
                        modelChartExemple77.totalDeaths = tunisianDetail!!.timelineitems[0].a78!!.totalDeaths
                        list.add(modelChartExemple77)

                        modelChartExemple78.reportDate = "21/05/2020"
                        modelChartExemple78.totalConfirmed = tunisianDetail!!.timelineitems[0].a79!!.totalCases
                        modelChartExemple78.totalDeaths = tunisianDetail!!.timelineitems[0].a79!!.totalDeaths
                        list.add(modelChartExemple78)

                        modelChartExemple79.reportDate = "22/05/2020"
                        modelChartExemple79.totalConfirmed = tunisianDetail!!.timelineitems[0].a80!!.totalCases
                        modelChartExemple79.totalDeaths = tunisianDetail!!.timelineitems[0].a80!!.totalDeaths
                        list.add(modelChartExemple79)

                        modelChartExemple80.reportDate = "23/05/2020"
                        modelChartExemple80.totalConfirmed = tunisianDetail!!.timelineitems[0].a81!!.totalCases
                        modelChartExemple80.totalDeaths = tunisianDetail!!.timelineitems[0].a81!!.totalDeaths
                        list.add(modelChartExemple80)

                        modelChartExemple81.reportDate = "24/05/2020"
                        modelChartExemple81.totalConfirmed = tunisianDetail!!.timelineitems[0].a82!!.totalCases
                        modelChartExemple81.totalDeaths = tunisianDetail!!.timelineitems[0].a82!!.totalDeaths
                        list.add(modelChartExemple81)

                        modelChartExemple82.reportDate = "25/05/2020"
                        modelChartExemple82.totalConfirmed = tunisianDetail!!.timelineitems[0].a83!!.totalCases
                        modelChartExemple82.totalDeaths = tunisianDetail!!.timelineitems[0].a83!!.totalDeaths
                        list.add(modelChartExemple82)

                        modelChartExemple83.reportDate = "26/05/2020"
                        modelChartExemple83.totalConfirmed = tunisianDetail!!.timelineitems[0].a84!!.totalCases
                        modelChartExemple83.totalDeaths = tunisianDetail!!.timelineitems[0].a84!!.totalDeaths
                        list.add(modelChartExemple83)

                        modelChartExemple84.reportDate = "27/05/2020"
                        modelChartExemple84.totalConfirmed = tunisianDetail!!.timelineitems[0].a85!!.totalCases
                        modelChartExemple84.totalDeaths = tunisianDetail!!.timelineitems[0].a85!!.totalDeaths
                        list.add(modelChartExemple84)

                        modelChartExemple85.reportDate = "28/05/2020"
                        modelChartExemple85.totalConfirmed = tunisianDetail!!.timelineitems[0].a86!!.totalCases
                        modelChartExemple85.totalDeaths = tunisianDetail!!.timelineitems[0].a86!!.totalDeaths
                        list.add(modelChartExemple85)

                        modelChartExemple86.reportDate = "29/05/2020"
                        modelChartExemple86.totalConfirmed = tunisianDetail!!.timelineitems[0].a87!!.totalCases
                        modelChartExemple86.totalDeaths = tunisianDetail!!.timelineitems[0].a87!!.totalDeaths
                        list.add(modelChartExemple86)

                        modelChartExemple87.reportDate = "30/05/2020"
                        modelChartExemple87.totalConfirmed = tunisianDetail!!.timelineitems[0].a88!!.totalCases
                        modelChartExemple87.totalDeaths = tunisianDetail!!.timelineitems[0].a88!!.totalDeaths
                        list.add(modelChartExemple87)

                        modelChartExemple88.reportDate = "31/05/2020"
                        modelChartExemple88.totalConfirmed = tunisianDetail!!.timelineitems[0].a89!!.totalCases
                        modelChartExemple88.totalDeaths = tunisianDetail!!.timelineitems[0].a89!!.totalDeaths
                        list.add(modelChartExemple88)

                        modelChartExemple89.reportDate = "01/06/2020"
                        modelChartExemple89.totalConfirmed = tunisianDetail!!.timelineitems[0].a90!!.totalCases
                        modelChartExemple89.totalDeaths = tunisianDetail!!.timelineitems[0].a90!!.totalDeaths
                        list.add(modelChartExemple89)

                        modelChartExemple90.reportDate = "02/06/2020"
                        modelChartExemple90.totalConfirmed = tunisianDetail!!.timelineitems[0].a91!!.totalCases
                        modelChartExemple90.totalDeaths = tunisianDetail!!.timelineitems[0].a91!!.totalDeaths
                        list.add(modelChartExemple90)

                        modelChartExemple91.reportDate = "03/06/2020"
                        modelChartExemple91.totalConfirmed = tunisianDetail!!.timelineitems[0].a92!!.totalCases
                        modelChartExemple91.totalDeaths = tunisianDetail!!.timelineitems[0].a92!!.totalDeaths
                        list.add(modelChartExemple91)

                        modelChartExemple92.reportDate = "04/06/2020"
                        modelChartExemple92.totalConfirmed = tunisianDetail!!.timelineitems[0].a93!!.totalCases
                        modelChartExemple92.totalDeaths = tunisianDetail!!.timelineitems[0].a93!!.totalDeaths
                        list.add(modelChartExemple92)

                        modelChartExemple93.reportDate = "05/06/2020"
                        modelChartExemple93.totalConfirmed = tunisianDetail!!.timelineitems[0].a94!!.totalCases
                        modelChartExemple93.totalDeaths = tunisianDetail!!.timelineitems[0].a94!!.totalDeaths
                        list.add(modelChartExemple93)

                        modelChartExemple94.reportDate = "06/06/2020"
                        modelChartExemple94.totalConfirmed = tunisianDetail!!.timelineitems[0].a95!!.totalCases
                        modelChartExemple94.totalDeaths = tunisianDetail!!.timelineitems[0].a95!!.totalDeaths
                        list.add(modelChartExemple94)

                        modelChartExemple95.reportDate = "07/06/2020"
                        modelChartExemple95.totalConfirmed = tunisianDetail!!.timelineitems[0].a96!!.totalCases
                        modelChartExemple95.totalDeaths = tunisianDetail!!.timelineitems[0].a96!!.totalDeaths
                        list.add(modelChartExemple95)

                        modelChartExemple96.reportDate = "08/06/2020"
                        modelChartExemple96.totalConfirmed = tunisianDetail!!.timelineitems[0].a97!!.totalCases
                        modelChartExemple96.totalDeaths = tunisianDetail!!.timelineitems[0].a97!!.totalDeaths
                        list.add(modelChartExemple96)

                        modelChartExemple97.reportDate = "09/06/2020"
                        modelChartExemple97.totalConfirmed = tunisianDetail!!.timelineitems[0].a98!!.totalCases
                        modelChartExemple97.totalDeaths = tunisianDetail!!.timelineitems[0].a98!!.totalDeaths
                        list.add(modelChartExemple97)

                        modelChartExemple98.reportDate = "10/06/2020"
                        modelChartExemple98.totalConfirmed = tunisianDetail!!.timelineitems[0].a99!!.totalCases
                        modelChartExemple98.totalDeaths = tunisianDetail!!.timelineitems[0].a99!!.totalDeaths
                        list.add(modelChartExemple98)

                        modelChartExemple99.reportDate = "11/06/2020"
                        modelChartExemple99.totalConfirmed = tunisianDetail!!.timelineitems[0].a100!!.totalCases
                        modelChartExemple99.totalDeaths = tunisianDetail!!.timelineitems[0].a100!!.totalDeaths
                        list.add(modelChartExemple99)

                        modelChartExemple100.reportDate = "12/06/2020"
                        modelChartExemple100.totalConfirmed = tunisianDetail!!.timelineitems[0].a101!!.totalCases
                        modelChartExemple100.totalDeaths = tunisianDetail!!.timelineitems[0].a101!!.totalDeaths
                        list.add(modelChartExemple100)

                        modelChartExemple101.reportDate = "13/06/2020"
                        modelChartExemple101.totalConfirmed = tunisianDetail!!.timelineitems[0].a102!!.totalCases
                        modelChartExemple101.totalDeaths = tunisianDetail!!.timelineitems[0].a102!!.totalDeaths
                        list.add(modelChartExemple101)

                        modelChartExemple102.reportDate = "14/06/2020"
                        modelChartExemple102.totalConfirmed = tunisianDetail!!.timelineitems[0].a103!!.totalCases
                        modelChartExemple102.totalDeaths = tunisianDetail!!.timelineitems[0].a103!!.totalDeaths
                        list.add(modelChartExemple102)

                        modelChartExemple103.reportDate = "15/06/2020"
                        modelChartExemple103.totalConfirmed = tunisianDetail!!.timelineitems[0].a104!!.totalCases
                        modelChartExemple103.totalDeaths = tunisianDetail!!.timelineitems[0].a104!!.totalDeaths
                        list.add(modelChartExemple103)

                        modelChartExemple104.reportDate = "16/06/2020"
                        modelChartExemple104.totalConfirmed = tunisianDetail!!.timelineitems[0].a105!!.totalCases
                        modelChartExemple104.totalDeaths = tunisianDetail!!.timelineitems[0].a105!!.totalDeaths
                        list.add(modelChartExemple104)

                        modelChartExemple105.reportDate = "17/06/2020"
                        modelChartExemple105.totalConfirmed = tunisianDetail!!.timelineitems[0].a106!!.totalCases
                        modelChartExemple105.totalDeaths = tunisianDetail!!.timelineitems[0].a106!!.totalDeaths
                        list.add(modelChartExemple105)

                        modelChartExemple106.reportDate = "18/06/2020"
                        modelChartExemple106.totalConfirmed = tunisianDetail!!.timelineitems[0].a107!!.totalCases
                        modelChartExemple106.totalDeaths = tunisianDetail!!.timelineitems[0].a107!!.totalDeaths
                        list.add(modelChartExemple106)

                        modelChartExemple107.reportDate = "19/06/2020"
                        modelChartExemple107.totalConfirmed = tunisianDetail!!.timelineitems[0].a108!!.totalCases
                        modelChartExemple107.totalDeaths = tunisianDetail!!.timelineitems[0].a108!!.totalDeaths
                        list.add(modelChartExemple107)

                        modelChartExemple108.reportDate = "20/06/2020"
                        modelChartExemple108.totalConfirmed = tunisianDetail!!.timelineitems[0].a109!!.totalCases
                        modelChartExemple108.totalDeaths = tunisianDetail!!.timelineitems[0].a109!!.totalDeaths
                        list.add(modelChartExemple108)

                        modelChartExemple109.reportDate = "21/06/2020"
                        modelChartExemple109.totalConfirmed = tunisianDetail!!.timelineitems[0].a110!!.totalCases
                        modelChartExemple109.totalDeaths = tunisianDetail!!.timelineitems[0].a110!!.totalDeaths
                        list.add(modelChartExemple109)

                        modelChartExemple110.reportDate = "22/06/2020"
                        modelChartExemple110.totalConfirmed = tunisianDetail!!.timelineitems[0].a111!!.totalCases
                        modelChartExemple110.totalDeaths = tunisianDetail!!.timelineitems[0].a111!!.totalDeaths
                        list.add(modelChartExemple110)

                        modelChartExemple111.reportDate = "23/06/2020"
                        modelChartExemple111.totalConfirmed = tunisianDetail!!.timelineitems[0].a112!!.totalCases
                        modelChartExemple111.totalDeaths = tunisianDetail!!.timelineitems[0].a112!!.totalDeaths
                        list.add(modelChartExemple111)

                        modelChartExemple112.reportDate = "24/06/2020"
                        modelChartExemple112.totalConfirmed = tunisianDetail!!.timelineitems[0].a113!!.totalCases
                        modelChartExemple112.totalDeaths = tunisianDetail!!.timelineitems[0].a113!!.totalDeaths
                        list.add(modelChartExemple112)

                        modelChartExemple113.reportDate = "25/06/2020"
                        modelChartExemple113.totalConfirmed = tunisianDetail!!.timelineitems[0].a114!!.totalCases
                        modelChartExemple113.totalDeaths = tunisianDetail!!.timelineitems[0].a114!!.totalDeaths
                        list.add(modelChartExemple113)

                        modelChartExemple114.reportDate = "26/06/2020"
                        modelChartExemple114.totalConfirmed = tunisianDetail!!.timelineitems[0].a115!!.totalCases
                        modelChartExemple114.totalDeaths = tunisianDetail!!.timelineitems[0].a115!!.totalDeaths
                        list.add(modelChartExemple114)

                        modelChartExemple115.reportDate = "27/06/2020"
                        modelChartExemple115.totalConfirmed = tunisianDetail!!.timelineitems[0].a116!!.totalCases
                        modelChartExemple115.totalDeaths = tunisianDetail!!.timelineitems[0].a116!!.totalDeaths
                        list.add(modelChartExemple115)

                        modelChartExemple116.reportDate = "28/06/2020"
                        modelChartExemple116.totalConfirmed = tunisianDetail!!.timelineitems[0].a117!!.totalCases
                        modelChartExemple116.totalDeaths = tunisianDetail!!.timelineitems[0].a117!!.totalDeaths
                        list.add(modelChartExemple116)

                        modelChartExemple117.reportDate = "29/06/2020"
                        modelChartExemple117.totalConfirmed = tunisianDetail!!.timelineitems[0].a118!!.totalCases
                        modelChartExemple117.totalDeaths = tunisianDetail!!.timelineitems[0].a118!!.totalDeaths
                        list.add(modelChartExemple117)

                        modelChartExemple118.reportDate = "30/06/2020"
                        modelChartExemple118.totalConfirmed = tunisianDetail!!.timelineitems[0].a119!!.totalCases
                        modelChartExemple118.totalDeaths = tunisianDetail!!.timelineitems[0].a119!!.totalDeaths
                        list.add(modelChartExemple118)

                        modelChartExemple119.reportDate = "01/07/2020"
                        modelChartExemple119.totalConfirmed = tunisianDetail!!.timelineitems[0].a120!!.totalCases
                        modelChartExemple119.totalDeaths = tunisianDetail!!.timelineitems[0].a120!!.totalDeaths
                        list.add(modelChartExemple119)

                        modelChartExemple120.reportDate = "02/07/2020"
                        modelChartExemple120.totalConfirmed = tunisianDetail!!.timelineitems[0].a121!!.totalCases
                        modelChartExemple120.totalDeaths = tunisianDetail!!.timelineitems[0].a121!!.totalDeaths
                        list.add(modelChartExemple120)

                        modelChartExemple121.reportDate = "03/07/2020"
                        modelChartExemple121.totalConfirmed = tunisianDetail!!.timelineitems[0].a122!!.totalCases
                        modelChartExemple121.totalDeaths = tunisianDetail!!.timelineitems[0].a122!!.totalDeaths
                        list.add(modelChartExemple121)

                        modelChartExemple122.reportDate = "04/07/2020"
                        modelChartExemple122.totalConfirmed = tunisianDetail!!.timelineitems[0].a123!!.totalCases
                        modelChartExemple122.totalDeaths = tunisianDetail!!.timelineitems[0].a123!!.totalDeaths
                        list.add(modelChartExemple122)

                        modelChartExemple123.reportDate = "05/07/2020"
                        modelChartExemple123.totalConfirmed = tunisianDetail!!.timelineitems[0].a124!!.totalCases
                        modelChartExemple123.totalDeaths = tunisianDetail!!.timelineitems[0].a124!!.totalDeaths
                        list.add(modelChartExemple123)

                        modelChartExemple124.reportDate = "06/07/2020"
                        modelChartExemple124.totalConfirmed = tunisianDetail!!.timelineitems[0].a125!!.totalCases
                        modelChartExemple124.totalDeaths = tunisianDetail!!.timelineitems[0].a125!!.totalDeaths
                        list.add(modelChartExemple124)

                        modelChartExemple125.reportDate = "07/07/2020"
                        modelChartExemple125.totalConfirmed = tunisianDetail!!.timelineitems[0].a126!!.totalCases
                        modelChartExemple125.totalDeaths = tunisianDetail!!.timelineitems[0].a126!!.totalDeaths
                        list.add(modelChartExemple125)

                        modelChartExemple126.reportDate = "08/07/2020"
                        modelChartExemple126.totalConfirmed = tunisianDetail!!.timelineitems[0].a127!!.totalCases
                        modelChartExemple126.totalDeaths = tunisianDetail!!.timelineitems[0].a127!!.totalDeaths
                        list.add(modelChartExemple126)

                        modelChartExemple127.reportDate = "09/07/2020"
                        modelChartExemple127.totalConfirmed = tunisianDetail!!.timelineitems[0].a128!!.totalCases
                        modelChartExemple127.totalDeaths = tunisianDetail!!.timelineitems[0].a128!!.totalDeaths
                        list.add(modelChartExemple127)

                        modelChartExemple128.reportDate = "10/07/2020"
                        modelChartExemple128.totalConfirmed = tunisianDetail!!.timelineitems[0].a129!!.totalCases
                        modelChartExemple128.totalDeaths = tunisianDetail!!.timelineitems[0].a129!!.totalDeaths
                        list.add(modelChartExemple128)

                        modelChartExemple129.reportDate = "11/07/2020"
                        modelChartExemple129.totalConfirmed = tunisianDetail!!.timelineitems[0].a130!!.totalCases
                        modelChartExemple129.totalDeaths = tunisianDetail!!.timelineitems[0].a130!!.totalDeaths
                        list.add(modelChartExemple129)

                        modelChartExemple130.reportDate = "12/07/2020"
                        modelChartExemple130.totalConfirmed = tunisianDetail!!.timelineitems[0].a131!!.totalCases
                        modelChartExemple130.totalDeaths = tunisianDetail!!.timelineitems[0].a131!!.totalDeaths
                        list.add(modelChartExemple130)

                        modelChartExemple131.reportDate = "13/07/2020"
                        modelChartExemple131.totalConfirmed = tunisianDetail!!.timelineitems[0].a132!!.totalCases
                        modelChartExemple131.totalDeaths = tunisianDetail!!.timelineitems[0].a132!!.totalDeaths
                        list.add(modelChartExemple131)

                        modelChartExemple132.reportDate = "14/07/2020"
                        modelChartExemple132.totalConfirmed = tunisianDetail!!.timelineitems[0].a133!!.totalCases
                        modelChartExemple132.totalDeaths = tunisianDetail!!.timelineitems[0].a133!!.totalDeaths
                        list.add(modelChartExemple132)

                        modelChartExemple133.reportDate = "15/07/2020"
                        modelChartExemple133.totalConfirmed = tunisianDetail!!.timelineitems[0].a134!!.totalCases
                        modelChartExemple133.totalDeaths = tunisianDetail!!.timelineitems[0].a134!!.totalDeaths
                        list.add(modelChartExemple133)

                        modelChartExemple134.reportDate = "16/07/2020"
                        modelChartExemple134.totalConfirmed = tunisianDetail!!.timelineitems[0].a135!!.totalCases
                        modelChartExemple134.totalDeaths = tunisianDetail!!.timelineitems[0].a135!!.totalDeaths
                        list.add(modelChartExemple134)

                        modelChartExemple135.reportDate = "17/07/2020"
                        modelChartExemple135.totalConfirmed = tunisianDetail!!.timelineitems[0].a136!!.totalCases
                        modelChartExemple135.totalDeaths = tunisianDetail!!.timelineitems[0].a136!!.totalDeaths
                        list.add(modelChartExemple135)

                        modelChartExemple136.reportDate = "18/07/2020"
                        modelChartExemple136.totalConfirmed = tunisianDetail!!.timelineitems[0].a137!!.totalCases
                        modelChartExemple136.totalDeaths = tunisianDetail!!.timelineitems[0].a137!!.totalDeaths
                        list.add(modelChartExemple136)

                        modelChartExemple137.reportDate = "19/07/2020"
                        modelChartExemple137.totalConfirmed = tunisianDetail!!.timelineitems[0].a138!!.totalCases
                        modelChartExemple137.totalDeaths = tunisianDetail!!.timelineitems[0].a138!!.totalDeaths
                        list.add(modelChartExemple137)

                        modelChartExemple138.reportDate = "20/07/2020"
                        modelChartExemple138.totalConfirmed = tunisianDetail!!.timelineitems[0].a139!!.totalCases
                        modelChartExemple138.totalDeaths = tunisianDetail!!.timelineitems[0].a139!!.totalDeaths
                        list.add(modelChartExemple138)

                        modelChartExemple139.reportDate = "21/07/2020"
                        modelChartExemple139.totalConfirmed = tunisianDetail!!.timelineitems[0].a140!!.totalCases
                        modelChartExemple139.totalDeaths = tunisianDetail!!.timelineitems[0].a140!!.totalDeaths
                        list.add(modelChartExemple139)

                        modelChartExemple140.reportDate = "22/07/2020"
                        modelChartExemple140.totalConfirmed = tunisianDetail!!.timelineitems[0].a141!!.totalCases
                        modelChartExemple140.totalDeaths = tunisianDetail!!.timelineitems[0].a141!!.totalDeaths
                        list.add(modelChartExemple140)

                        modelChartExemple141.reportDate = "23/07/2020"
                        modelChartExemple141.totalConfirmed = tunisianDetail!!.timelineitems[0].a142!!.totalCases
                        modelChartExemple141.totalDeaths = tunisianDetail!!.timelineitems[0].a142!!.totalDeaths
                        list.add(modelChartExemple141)

                        modelChartExemple142.reportDate = "24/07/2020"
                        modelChartExemple142.totalConfirmed = tunisianDetail!!.timelineitems[0].a143!!.totalCases
                        modelChartExemple142.totalDeaths = tunisianDetail!!.timelineitems[0].a143!!.totalDeaths
                        list.add(modelChartExemple142)

                        modelChartExemple143.reportDate = "25/07/2020"
                        modelChartExemple143.totalConfirmed = tunisianDetail!!.timelineitems[0].a144!!.totalCases
                        modelChartExemple143.totalDeaths = tunisianDetail!!.timelineitems[0].a144!!.totalDeaths
                        list.add(modelChartExemple143)

                        modelChartExemple144.reportDate = "26/07/2020"
                        modelChartExemple144.totalConfirmed = tunisianDetail!!.timelineitems[0].a145!!.totalCases
                        modelChartExemple144.totalDeaths = tunisianDetail!!.timelineitems[0].a145!!.totalDeaths
                        list.add(modelChartExemple144)

                        modelChartExemple145.reportDate = "27/07/2020"
                        modelChartExemple145.totalConfirmed = tunisianDetail!!.timelineitems[0].a146!!.totalCases
                        modelChartExemple145.totalDeaths = tunisianDetail!!.timelineitems[0].a146!!.totalDeaths
                        list.add(modelChartExemple145)

                        modelChartExemple146.reportDate = "28/07/2020"
                        modelChartExemple146.totalConfirmed = tunisianDetail!!.timelineitems[0].a147!!.totalCases
                        modelChartExemple146.totalDeaths = tunisianDetail!!.timelineitems[0].a147!!.totalDeaths
                        list.add(modelChartExemple146)

                        modelChartExemple147.reportDate = "29/07/2020"
                        modelChartExemple147.totalConfirmed = tunisianDetail!!.timelineitems[0].a148!!.totalCases
                        modelChartExemple147.totalDeaths = tunisianDetail!!.timelineitems[0].a148!!.totalDeaths
                        list.add(modelChartExemple147)

                        modelChartExemple148.reportDate = "30/07/2020"
                        modelChartExemple148.totalConfirmed = tunisianDetail!!.timelineitems[0].a149!!.totalCases
                        modelChartExemple148.totalDeaths = tunisianDetail!!.timelineitems[0].a149!!.totalDeaths
                        list.add(modelChartExemple148)

                        modelChartExemple149.reportDate = "31/07/2020"
                        modelChartExemple149.totalConfirmed = tunisianDetail!!.timelineitems[0].a150!!.totalCases
                        modelChartExemple149.totalDeaths = tunisianDetail!!.timelineitems[0].a150!!.totalDeaths
                        list.add(modelChartExemple149)

                        modelChartExemple150.reportDate = "01/08/2020"
                        modelChartExemple150.totalConfirmed = tunisianDetail!!.timelineitems[0].a151!!.totalCases
                        modelChartExemple150.totalDeaths = tunisianDetail!!.timelineitems[0].a151!!.totalDeaths
                        list.add(modelChartExemple150)

                        modelChartExemple151.reportDate = "02/08/2020"
                        modelChartExemple151.totalConfirmed = tunisianDetail!!.timelineitems[0].a152!!.totalCases
                        modelChartExemple151.totalDeaths = tunisianDetail!!.timelineitems[0].a152!!.totalDeaths
                        list.add(modelChartExemple151)

                        modelChartExemple152.reportDate = "03/08/2020"
                        modelChartExemple152.totalConfirmed = tunisianDetail!!.timelineitems[0].a153!!.totalCases
                        modelChartExemple152.totalDeaths = tunisianDetail!!.timelineitems[0].a153!!.totalDeaths
                        list.add(modelChartExemple152)

                        modelChartExemple153.reportDate = "04/08/2020"
                        modelChartExemple153.totalConfirmed = tunisianDetail!!.timelineitems[0].a154!!.totalCases
                        modelChartExemple153.totalDeaths = tunisianDetail!!.timelineitems[0].a154!!.totalDeaths
                        list.add(modelChartExemple153)

                        modelChartExemple154.reportDate = "05/08/2020"
                        modelChartExemple154.totalConfirmed = tunisianDetail!!.timelineitems[0].a155!!.totalCases
                        modelChartExemple154.totalDeaths = tunisianDetail!!.timelineitems[0].a155!!.totalDeaths
                        list.add(modelChartExemple154)

                        modelChartExemple155.reportDate = "06/08/2020"
                        modelChartExemple155.totalConfirmed = tunisianDetail!!.timelineitems[0].a156!!.totalCases
                        modelChartExemple155.totalDeaths = tunisianDetail!!.timelineitems[0].a156!!.totalDeaths
                        list.add(modelChartExemple155)

                        modelChartExemple156.reportDate = "07/08/2020"
                        modelChartExemple156.totalConfirmed = tunisianDetail!!.timelineitems[0].a157!!.totalCases
                        modelChartExemple156.totalDeaths = tunisianDetail!!.timelineitems[0].a157!!.totalDeaths
                        list.add(modelChartExemple156)

                        modelChartExemple157.reportDate = "08/08/2020"
                        modelChartExemple157.totalConfirmed = tunisianDetail!!.timelineitems[0].a158!!.totalCases
                        modelChartExemple157.totalDeaths = tunisianDetail!!.timelineitems[0].a158!!.totalDeaths
                        list.add(modelChartExemple157)

                        modelChartExemple158.reportDate = "09/08/2020"
                        modelChartExemple158.totalConfirmed = tunisianDetail!!.timelineitems[0].a159!!.totalCases
                        modelChartExemple158.totalDeaths = tunisianDetail!!.timelineitems[0].a159!!.totalDeaths
                        list.add(modelChartExemple158)

                        modelChartExemple159.reportDate = "10/08/2020"
                        modelChartExemple159.totalConfirmed = tunisianDetail!!.timelineitems[0].a160!!.totalCases
                        modelChartExemple159.totalDeaths = tunisianDetail!!.timelineitems[0].a160!!.totalDeaths
                        list.add(modelChartExemple159)

                        modelChartExemple160.reportDate = "11/08/2020"
                        modelChartExemple160.totalConfirmed = tunisianDetail!!.timelineitems[0].a161!!.totalCases
                        modelChartExemple160.totalDeaths = tunisianDetail!!.timelineitems[0].a161!!.totalDeaths
                        list.add(modelChartExemple160)

                        modelChartExemple161.reportDate = "12/08/2020"
                        modelChartExemple161.totalConfirmed = tunisianDetail!!.timelineitems[0].a162!!.totalCases
                        modelChartExemple161.totalDeaths = tunisianDetail!!.timelineitems[0].a162!!.totalDeaths
                        list.add(modelChartExemple161)

                        modelChartExemple162.reportDate = "13/08/2020"
                        modelChartExemple162.totalConfirmed = tunisianDetail!!.timelineitems[0].a163!!.totalCases
                        modelChartExemple162.totalDeaths = tunisianDetail!!.timelineitems[0].a163!!.totalDeaths
                        list.add(modelChartExemple162)

                        modelChartExemple163.reportDate = "14/08/2020"
                        modelChartExemple163.totalConfirmed = tunisianDetail!!.timelineitems[0].a164!!.totalCases
                        modelChartExemple163.totalDeaths = tunisianDetail!!.timelineitems[0].a164!!.totalDeaths
                        list.add(modelChartExemple163)

                        modelChartExemple164.reportDate = "15/08/2020"
                        modelChartExemple164.totalConfirmed = tunisianDetail!!.timelineitems[0].a165!!.totalCases
                        modelChartExemple164.totalDeaths = tunisianDetail!!.timelineitems[0].a165!!.totalDeaths
                        list.add(modelChartExemple164)

                        modelChartExemple165.reportDate = "16/08/2020"
                        modelChartExemple165.totalConfirmed = tunisianDetail!!.timelineitems[0].a166!!.totalCases
                        modelChartExemple165.totalDeaths = tunisianDetail!!.timelineitems[0].a166!!.totalDeaths
                        list.add(modelChartExemple165)

                        modelChartExemple166.reportDate = "17/08/2020"
                        modelChartExemple166.totalConfirmed = tunisianDetail!!.timelineitems[0].a167!!.totalCases
                        modelChartExemple166.totalDeaths = tunisianDetail!!.timelineitems[0].a167!!.totalDeaths
                        list.add(modelChartExemple166)

                        modelChartExemple167.reportDate = "18/08/2020"
                        modelChartExemple167.totalConfirmed = tunisianDetail!!.timelineitems[0].a168!!.totalCases
                        modelChartExemple167.totalDeaths = tunisianDetail!!.timelineitems[0].a168!!.totalDeaths
                        list.add(modelChartExemple167)

                        modelChartExemple168.reportDate = "19/08/2020"
                        modelChartExemple168.totalConfirmed = tunisianDetail!!.timelineitems[0].a169!!.totalCases
                        modelChartExemple168.totalDeaths = tunisianDetail!!.timelineitems[0].a169!!.totalDeaths
                        list.add(modelChartExemple168)

                        modelChartExemple169.reportDate = "20/08/2020"
                        modelChartExemple169.totalConfirmed = tunisianDetail!!.timelineitems[0].a170!!.totalCases
                        modelChartExemple169.totalDeaths = tunisianDetail!!.timelineitems[0].a170!!.totalDeaths
                        list.add(modelChartExemple169)

                        modelChartExemple170.reportDate = "21/08/2020"
                        modelChartExemple170.totalConfirmed = tunisianDetail!!.timelineitems[0].a171!!.totalCases
                        modelChartExemple170.totalDeaths = tunisianDetail!!.timelineitems[0].a171!!.totalDeaths
                        list.add(modelChartExemple170)

                        modelChartExemple171.reportDate = "22/08/2020"
                        modelChartExemple171.totalConfirmed = tunisianDetail!!.timelineitems[0].a172!!.totalCases
                        modelChartExemple171.totalDeaths = tunisianDetail!!.timelineitems[0].a172!!.totalDeaths
                        list.add(modelChartExemple171)

                        modelChartExemple172.reportDate = "23/08/2020"
                        modelChartExemple172.totalConfirmed = tunisianDetail!!.timelineitems[0].a173!!.totalCases
                        modelChartExemple172.totalDeaths = tunisianDetail!!.timelineitems[0].a173!!.totalDeaths
                        list.add(modelChartExemple172)

                        modelChartExemple173.reportDate = "24/08/2020"
                        modelChartExemple173.totalConfirmed = tunisianDetail!!.timelineitems[0].a174!!.totalCases
                        modelChartExemple173.totalDeaths = tunisianDetail!!.timelineitems[0].a174!!.totalDeaths
                        list.add(modelChartExemple173)

                        modelChartExemple174.reportDate = "25/08/2020"
                        modelChartExemple174.totalConfirmed = tunisianDetail!!.timelineitems[0].a175!!.totalCases
                        modelChartExemple174.totalDeaths = tunisianDetail!!.timelineitems[0].a175!!.totalDeaths
                        list.add(modelChartExemple174)

                        modelChartExemple175.reportDate = "26/08/2020"
                        modelChartExemple175.totalConfirmed = tunisianDetail!!.timelineitems[0].a176!!.totalCases
                        modelChartExemple175.totalDeaths = tunisianDetail!!.timelineitems[0].a176!!.totalDeaths
                        list.add(modelChartExemple175)

                        modelChartExemple176.reportDate = "27/08/2020"
                        modelChartExemple176.totalConfirmed = tunisianDetail!!.timelineitems[0].a177!!.totalCases
                        modelChartExemple176.totalDeaths = tunisianDetail!!.timelineitems[0].a177!!.totalDeaths
                        list.add(modelChartExemple176)

                        modelChartExemple177.reportDate = "28/08/2020"
                        modelChartExemple177.totalConfirmed = tunisianDetail!!.timelineitems[0].a178!!.totalCases
                        modelChartExemple177.totalDeaths = tunisianDetail!!.timelineitems[0].a178!!.totalDeaths
                        list.add(modelChartExemple177)

                        modelChartExemple178.reportDate = "29/08/2020"
                        modelChartExemple178.totalConfirmed = tunisianDetail!!.timelineitems[0].a179!!.totalCases
                        modelChartExemple178.totalDeaths = tunisianDetail!!.timelineitems[0].a179!!.totalDeaths
                        list.add(modelChartExemple178)

                        modelChartExemple179.reportDate = "30/08/2020"
                        modelChartExemple179.totalConfirmed = tunisianDetail!!.timelineitems[0].a180!!.totalCases
                        modelChartExemple179.totalDeaths = tunisianDetail!!.timelineitems[0].a180!!.totalDeaths
                        list.add(modelChartExemple179)

                        modelChartExemple180.reportDate = "31/08/2020"
                        modelChartExemple180.totalConfirmed = tunisianDetail!!.timelineitems[0].a181!!.totalCases
                        modelChartExemple180.totalDeaths = tunisianDetail!!.timelineitems[0].a181!!.totalDeaths
                        list.add(modelChartExemple180)

                        modelChartExemple181.reportDate = "01/09/2020"
                        modelChartExemple181.totalConfirmed = tunisianDetail!!.timelineitems[0].a182!!.totalCases
                        modelChartExemple181.totalDeaths = tunisianDetail!!.timelineitems[0].a182!!.totalDeaths
                        list.add(modelChartExemple181)

                        modelChartExemple182.reportDate = "02/09/2020"
                        modelChartExemple182.totalConfirmed = tunisianDetail!!.timelineitems[0].a183!!.totalCases
                        modelChartExemple182.totalDeaths = tunisianDetail!!.timelineitems[0].a183!!.totalDeaths
                        list.add(modelChartExemple182)

                        modelChartExemple183.reportDate = "03/09/2020"
                        modelChartExemple183.totalConfirmed = tunisianDetail!!.timelineitems[0].a184!!.totalCases
                        modelChartExemple183.totalDeaths = tunisianDetail!!.timelineitems[0].a184!!.totalDeaths
                        list.add(modelChartExemple183)

                        modelChartExemple184.reportDate = "04/09/2020"
                        modelChartExemple184.totalConfirmed = tunisianDetail!!.timelineitems[0].a185!!.totalCases
                        modelChartExemple184.totalDeaths = tunisianDetail!!.timelineitems[0].a185!!.totalDeaths
                        list.add(modelChartExemple184)

                        modelChartExemple185.reportDate = "05/09/2020"
                        modelChartExemple185.totalConfirmed = tunisianDetail!!.timelineitems[0].a186!!.totalCases
                        modelChartExemple185.totalDeaths = tunisianDetail!!.timelineitems[0].a186!!.totalDeaths
                        list.add(modelChartExemple185)

                        modelChartExemple186.reportDate = "06/09/2020"
                        modelChartExemple186.totalConfirmed = tunisianDetail!!.timelineitems[0].a187!!.totalCases
                        modelChartExemple186.totalDeaths = tunisianDetail!!.timelineitems[0].a187!!.totalDeaths
                        list.add(modelChartExemple186)

                        modelChartExemple187.reportDate = "07/09/2020"
                        modelChartExemple187.totalConfirmed = tunisianDetail!!.timelineitems[0].a188!!.totalCases
                        modelChartExemple187.totalDeaths = tunisianDetail!!.timelineitems[0].a188!!.totalDeaths
                        list.add(modelChartExemple187)

                        modelChartExemple188.reportDate = "08/09/2020"
                        modelChartExemple188.totalConfirmed = tunisianDetail!!.timelineitems[0].a189!!.totalCases
                        modelChartExemple188.totalDeaths = tunisianDetail!!.timelineitems[0].a189!!.totalDeaths
                        list.add(modelChartExemple188)

                        modelChartExemple189.reportDate = "09/09/2020"
                        modelChartExemple189.totalConfirmed = tunisianDetail!!.timelineitems[0].a190!!.totalCases
                        modelChartExemple189.totalDeaths = tunisianDetail!!.timelineitems[0].a190!!.totalDeaths
                        list.add(modelChartExemple189)

                        modelChartExemple190.reportDate = "10/09/2020"
                        modelChartExemple190.totalConfirmed = tunisianDetail!!.timelineitems[0].a191!!.totalCases
                        modelChartExemple190.totalDeaths = tunisianDetail!!.timelineitems[0].a191!!.totalDeaths
                        list.add(modelChartExemple190)

                        modelChartExemple191.reportDate = "11/09/2020"
                        modelChartExemple191.totalConfirmed = tunisianDetail!!.timelineitems[0].a192!!.totalCases
                        modelChartExemple191.totalDeaths = tunisianDetail!!.timelineitems[0].a192!!.totalDeaths
                        list.add(modelChartExemple191)

                        modelChartExemple192.reportDate = "12/09/2020"
                        modelChartExemple192.totalConfirmed = tunisianDetail!!.timelineitems[0].a193!!.totalCases
                        modelChartExemple192.totalDeaths = tunisianDetail!!.timelineitems[0].a193!!.totalDeaths
                        list.add(modelChartExemple192)

                        modelChartExemple193.reportDate = "13/09/2020"
                        modelChartExemple193.totalConfirmed = tunisianDetail!!.timelineitems[0].a194!!.totalCases
                        modelChartExemple193.totalDeaths = tunisianDetail!!.timelineitems[0].a194!!.totalDeaths
                        list.add(modelChartExemple193)

                        modelChartExemple194.reportDate = "14/09/2020"
                        modelChartExemple194.totalConfirmed = tunisianDetail!!.timelineitems[0].a195!!.totalCases
                        modelChartExemple194.totalDeaths = tunisianDetail!!.timelineitems[0].a195!!.totalDeaths
                        list.add(modelChartExemple194)

                        modelChartExemple195.reportDate = "15/09/2020"
                        modelChartExemple195.totalConfirmed = tunisianDetail!!.timelineitems[0].a196!!.totalCases
                        modelChartExemple195.totalDeaths = tunisianDetail!!.timelineitems[0].a196!!.totalDeaths
                        list.add(modelChartExemple195)

                        modelChartExemple196.reportDate = "16/09/2020"
                        modelChartExemple196.totalConfirmed = tunisianDetail!!.timelineitems[0].a197!!.totalCases
                        modelChartExemple196.totalDeaths = tunisianDetail!!.timelineitems[0].a197!!.totalDeaths
                        list.add(modelChartExemple196)

                        modelChartExemple197.reportDate = "17/09/2020"
                        modelChartExemple197.totalConfirmed = tunisianDetail!!.timelineitems[0].a198!!.totalCases
                        modelChartExemple197.totalDeaths = tunisianDetail!!.timelineitems[0].a198!!.totalDeaths
                        list.add(modelChartExemple197)

                        modelChartExemple198.reportDate = "18/09/2020"
                        modelChartExemple198.totalConfirmed = tunisianDetail!!.timelineitems[0].a199!!.totalCases
                        modelChartExemple198.totalDeaths = tunisianDetail!!.timelineitems[0].a199!!.totalDeaths
                        list.add(modelChartExemple198)

                        modelChartExemple199.reportDate = "19/09/2020"
                        modelChartExemple199.totalConfirmed = tunisianDetail!!.timelineitems[0].a200!!.totalCases
                        modelChartExemple199.totalDeaths = tunisianDetail!!.timelineitems[0].a200!!.totalDeaths
                        list.add(modelChartExemple199)

                        modelChartExemple200.reportDate = "20/09/2020"
                        modelChartExemple200.totalConfirmed = tunisianDetail!!.timelineitems[0].a201!!.totalCases
                        modelChartExemple200.totalDeaths = tunisianDetail!!.timelineitems[0].a201!!.totalDeaths
                        list.add(modelChartExemple200)

                        modelChartExemple201.reportDate = "21/09/2020"
                        modelChartExemple201.totalConfirmed = tunisianDetail!!.timelineitems[0].a202!!.totalCases
                        modelChartExemple201.totalDeaths = tunisianDetail!!.timelineitems[0].a202!!.totalDeaths
                        list.add(modelChartExemple201)

                        modelChartExemple202.reportDate = "22/09/2020"
                        modelChartExemple202.totalConfirmed = tunisianDetail!!.timelineitems[0].a203!!.totalCases
                        modelChartExemple202.totalDeaths = tunisianDetail!!.timelineitems[0].a203!!.totalDeaths
                        list.add(modelChartExemple202)

                        modelChartExemple203.reportDate = "23/09/2020"
                        modelChartExemple203.totalConfirmed = tunisianDetail!!.timelineitems[0].a204!!.totalCases
                        modelChartExemple203.totalDeaths = tunisianDetail!!.timelineitems[0].a204!!.totalDeaths
                        list.add(modelChartExemple203)

                        modelChartExemple204.reportDate = "24/09/2020"
                        modelChartExemple204.totalConfirmed = tunisianDetail!!.timelineitems[0].a205!!.totalCases
                        modelChartExemple204.totalDeaths = tunisianDetail!!.timelineitems[0].a205!!.totalDeaths
                        list.add(modelChartExemple204)

                        modelChartExemple205.reportDate = "25/09/2020"
                        modelChartExemple205.totalConfirmed = tunisianDetail!!.timelineitems[0].a206!!.totalCases
                        modelChartExemple205.totalDeaths = tunisianDetail!!.timelineitems[0].a206!!.totalDeaths
                        list.add(modelChartExemple205)

                        modelChartExemple206.reportDate = "26/09/2020"
                        modelChartExemple206.totalConfirmed = tunisianDetail!!.timelineitems[0].a207!!.totalCases
                        modelChartExemple206.totalDeaths = tunisianDetail!!.timelineitems[0].a207!!.totalDeaths
                        list.add(modelChartExemple206)

                        modelChartExemple207.reportDate = "27/09/2020"
                        modelChartExemple207.totalConfirmed = tunisianDetail!!.timelineitems[0].a208!!.totalCases
                        modelChartExemple207.totalDeaths = tunisianDetail!!.timelineitems[0].a208!!.totalDeaths
                        list.add(modelChartExemple207)

                        modelChartExemple208.reportDate = "28/09/2020"
                        modelChartExemple208.totalConfirmed = tunisianDetail!!.timelineitems[0].a209!!.totalCases
                        modelChartExemple208.totalDeaths = tunisianDetail!!.timelineitems[0].a209!!.totalDeaths
                        list.add(modelChartExemple208)

                        modelChartExemple209.reportDate = "29/09/2020"
                        modelChartExemple209.totalConfirmed = tunisianDetail!!.timelineitems[0].a210!!.totalCases
                        modelChartExemple209.totalDeaths = tunisianDetail!!.timelineitems[0].a210!!.totalDeaths
                        list.add(modelChartExemple209)

                        modelChartExemple210.reportDate = "30/09/2020"
                        modelChartExemple210.totalConfirmed = tunisianDetail!!.timelineitems[0].a211!!.totalCases
                        modelChartExemple210.totalDeaths = tunisianDetail!!.timelineitems[0].a211!!.totalDeaths
                        list.add(modelChartExemple210)

                        modelChartExemple211.reportDate = "01/10/2020"
                        modelChartExemple211.totalConfirmed = tunisianDetail!!.timelineitems[0].a212!!.totalCases
                        modelChartExemple211.totalDeaths = tunisianDetail!!.timelineitems[0].a212!!.totalDeaths
                        list.add(modelChartExemple211)

                        modelChartExemple212.reportDate = "02/10/2020"
                        modelChartExemple212.totalConfirmed = tunisianDetail!!.timelineitems[0].a213!!.totalCases
                        modelChartExemple212.totalDeaths = tunisianDetail!!.timelineitems[0].a213!!.totalDeaths
                        list.add(modelChartExemple212)

                        modelChartExemple213.reportDate = "03/10/2020"
                        modelChartExemple213.totalConfirmed = tunisianDetail!!.timelineitems[0].a214!!.totalCases
                        modelChartExemple213.totalDeaths = tunisianDetail!!.timelineitems[0].a214!!.totalDeaths
                        list.add(modelChartExemple213)

                        modelChartExemple214.reportDate = "04/10/2020"
                        modelChartExemple214.totalConfirmed = tunisianDetail!!.timelineitems[0].a215!!.totalCases
                        modelChartExemple214.totalDeaths = tunisianDetail!!.timelineitems[0].a215!!.totalDeaths
                        list.add(modelChartExemple214)

                        modelChartExemple215.reportDate = "05/10/2020"
                        modelChartExemple215.totalConfirmed = tunisianDetail!!.timelineitems[0].a216!!.totalCases
                        modelChartExemple215.totalDeaths = tunisianDetail!!.timelineitems[0].a216!!.totalDeaths
                        list.add(modelChartExemple215)

                        modelChartExemple216.reportDate = "06/10/2020"
                        modelChartExemple216.totalConfirmed = tunisianDetail!!.timelineitems[0].a217!!.totalCases
                        modelChartExemple216.totalDeaths = tunisianDetail!!.timelineitems[0].a217!!.totalDeaths
                        list.add(modelChartExemple216)

                        modelChartExemple217.reportDate = "07/10/2020"
                        modelChartExemple217.totalConfirmed = tunisianDetail!!.timelineitems[0].a218!!.totalCases
                        modelChartExemple217.totalDeaths = tunisianDetail!!.timelineitems[0].a218!!.totalDeaths
                        list.add(modelChartExemple217)

                        modelChartExemple218.reportDate = "08/10/2020"
                        modelChartExemple218.totalConfirmed = tunisianDetail!!.timelineitems[0].a219!!.totalCases
                        modelChartExemple218.totalDeaths = tunisianDetail!!.timelineitems[0].a219!!.totalDeaths
                        list.add(modelChartExemple218)

                        modelChartExemple219.reportDate = "09/10/2020"
                        modelChartExemple219.totalConfirmed = tunisianDetail!!.timelineitems[0].a220!!.totalCases
                        modelChartExemple219.totalDeaths = tunisianDetail!!.timelineitems[0].a220!!.totalDeaths
                        list.add(modelChartExemple219)

                        modelChartExemple220.reportDate = "10/10/2020"
                        modelChartExemple220.totalConfirmed = tunisianDetail!!.timelineitems[0].a221!!.totalCases
                        modelChartExemple220.totalDeaths = tunisianDetail!!.timelineitems[0].a221!!.totalDeaths
                        list.add(modelChartExemple220)

                        modelChartExemple221.reportDate = "11/10/2020"
                        modelChartExemple221.totalConfirmed = tunisianDetail!!.timelineitems[0].a222!!.totalCases
                        modelChartExemple221.totalDeaths = tunisianDetail!!.timelineitems[0].a222!!.totalDeaths
                        list.add(modelChartExemple221)

                        modelChartExemple222.reportDate = "12/10/2020"
                        modelChartExemple222.totalConfirmed = tunisianDetail!!.timelineitems[0].a223!!.totalCases
                        modelChartExemple222.totalDeaths = tunisianDetail!!.timelineitems[0].a223!!.totalDeaths
                        list.add(modelChartExemple222)

                        modelChartExemple223.reportDate = "13/10/2020"
                        modelChartExemple223.totalConfirmed = tunisianDetail!!.timelineitems[0].a224!!.totalCases
                        modelChartExemple223.totalDeaths = tunisianDetail!!.timelineitems[0].a224!!.totalDeaths
                        list.add(modelChartExemple223)

                        modelChartExemple224.reportDate = "14/10/2020"
                        modelChartExemple224.totalConfirmed = tunisianDetail!!.timelineitems[0].a225!!.totalCases
                        modelChartExemple224.totalDeaths = tunisianDetail!!.timelineitems[0].a225!!.totalDeaths
                        list.add(modelChartExemple224)



                        tunisianChartDao.deleteCharts()
                        tunisianChartDao.addCharts(NumberUtils.convertListChart(list))

                    }
                }
            }

            override fun onFailure(call: Call<TunisianDetailResponse>, t: Throwable?) {

            }
        })
    }

    fun getMainModel(): LiveData<MainModelEntity> {

        return modelDao.all()
    }
}