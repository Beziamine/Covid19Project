package com.example.covidkotlinproject.ui.language.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.ui.main.activity.MainActivity
import com.example.covidkotlinproject.ui.splash.activity.SplashActivity
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.color
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_tunisian_detail.*
import java.util.*

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_language)

        val initLanguage : String? = NumberUtils.getLanguage(this)
        val initMode : String? = NumberUtils.getMode(this)

        when (initLanguage) {
            "en" -> {
                radio_group.check(english.id)
            }
            "fr" -> {
                radio_group.check(french.id)
            }
            else -> {
                radio_group.check(arabic.id)
            }
        }

        when (initMode) {
            "night" -> {
                radio_group_mode.check(night.id)
                switchNight()
            }
            else -> {
                radio_group_mode.check(day.id)
                switchDay()
            }
        }

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                val selectedLang :String = radio.text.toString()

                when (selectedLang) {
                    "English" -> {
                        switchLanguage("en")
                    }
                    "Français" -> {
                        switchLanguage("fr")
                    }
                    else -> {
                        switchLanguage("ar")
                    }
                }
            })


        radio_group_mode.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                val selectedMode :String = radio.text.toString()


                if (selectedMode == "Night Mode" || selectedMode == "Mode Nuit" || selectedMode == "ليل"){
                    switchNight()

                }else {
                    switchDay()
                }
            })

        confirm_language.setOnClickListener {

            val checkedId : Int = radio_group_mode.checkedRadioButtonId
            val radio: RadioButton = findViewById(checkedId)
            val mode :String = radio.text.toString()

            val checkedIdLang : Int = radio_group.checkedRadioButtonId
            val radioLang: RadioButton = findViewById(checkedIdLang)
            val language :String = radioLang.text.toString()

            if (mode == "Night Mode" || mode == "Mode Nuit" || mode == "ليل"){
                NumberUtils.setMode(this,"night")
            }else{
                NumberUtils.setMode(this,"day")
            }

            when (language) {
                "English" -> {
                    NumberUtils.setLanguage(this, "en")
                    setLocale("en")
                    NumberUtils.applyLanguage(this,"en")
                }
                "Français" -> {
                    NumberUtils.setLanguage(this, "fr")
                    setLocale("fr")
                    NumberUtils.applyLanguage(this,"fr")
                }
                else -> {
                    NumberUtils.setLanguage(this, "ar")
                    setLocale("ar")
                    NumberUtils.applyLanguage(this,"ar")
                }
            }

            NumberUtils.setFirstLaunch(this,false)
        }
    }

    private fun switchLanguage(s: String) {

        when (s) {
            "en" -> {
                confirm_language.text = "Go to Dashboard"
                select_language.text = "Please select your language"
                select_mode.text = "Display Mode"
                night.text = "Night Mode"
                day.text = "Day Mode"
                relative_languages.layoutDirection = View.LAYOUT_DIRECTION_LTR
            }
            "fr" -> {
                confirm_language.text = "Menu Principal"
                select_language.text = "Veuillez sélectionner votre langue"
                select_mode.text = "Mode d'affichage"
                night.text = "Mode Nuit"
                day.text = "Mode Jour"
                relative_languages.layoutDirection = View.LAYOUT_DIRECTION_LTR
            }
            else -> {
                confirm_language.text = "الصفحة الرئيسية"
                select_language.text = "الرجاء اختيار لغتك"
                select_mode.text = "إختيار الإضاءة"
                night.text = "ليل"
                day.text = "نهار"
                relative_languages.layoutDirection = View.LAYOUT_DIRECTION_RTL
            }
        }
    }

    private fun switchNight(){
        relative_activity_lang.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundPrimary))
        select_language.setTextColor(ContextCompat.getColor(this, R.color.white))
        english.setTextColor(ContextCompat.getColor(this, R.color.white))
        french.setTextColor(ContextCompat.getColor(this, R.color.white))
        arabic.setTextColor(ContextCompat.getColor(this, R.color.white))
        select_mode.setTextColor(ContextCompat.getColor(this, R.color.white))
        night.setTextColor(ContextCompat.getColor(this, R.color.white))
        day.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun switchDay(){
        relative_activity_lang.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundPrimaryDay))
        select_language.setTextColor(ContextCompat.getColor(this, R.color.black))
        english.setTextColor(ContextCompat.getColor(this, R.color.black))
        french.setTextColor(ContextCompat.getColor(this, R.color.black))
        arabic.setTextColor(ContextCompat.getColor(this, R.color.black))
        select_mode.setTextColor(ContextCompat.getColor(this, R.color.black))
        night.setTextColor(ContextCompat.getColor(this, R.color.black))
        day.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}