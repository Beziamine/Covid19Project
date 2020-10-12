package com.example.covidkotlinproject.ui.splash.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.covidkotlinproject.R
import com.example.covidkotlinproject.ui.language.activity.LanguageActivity
import com.example.covidkotlinproject.ui.main.activity.MainActivity
import com.example.covidkotlinproject.utils.NumberUtils
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val ANIMATION_DURATION:Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimation()
    }


    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION

        val language : String = NumberUtils.getLanguage(this)!!

        val intent : Intent = if (NumberUtils.getFirstLaunch(this)){
            Intent(this, LanguageActivity::class.java)
        }else{
            Intent(this, MainActivity::class.java)
        }

        NumberUtils.applyLanguage(this,language)
        setLocale(language)

        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                startActivity(intent)
                finish()
            }
            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

        })

        valueAnimator.start()
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)

    }
}
