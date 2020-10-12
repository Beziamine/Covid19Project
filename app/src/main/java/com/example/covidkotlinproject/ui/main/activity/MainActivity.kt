package com.example.covidkotlinproject.ui.main.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.utils.NumberUtils
import com.example.covidkotlinproject.utils.gone
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val initMode : String? = NumberUtils.getMode(this)


        when (initMode) {
            "night" -> {
                adView.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundPrimary))
            }
            else -> {
                adView.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundPrimaryDay))

            }
        }

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


}
