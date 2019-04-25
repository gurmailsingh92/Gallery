package com.media.gallery.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.media.gallery.Constants
import com.media.gallery.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var anim: YoYo.YoYoString? = null
    private var backPressed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animateCategoriesStrip()
    }

    private val listener = object : com.nineoldandroids.animation.Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: com.nineoldandroids.animation.Animator?) {

        }

        override fun onAnimationEnd(animation: com.nineoldandroids.animation.Animator?) {
            if (!backPressed) {
                val intent = Intent(this@SplashActivity, ImageListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        override fun onAnimationCancel(animation: com.nineoldandroids.animation.Animator?) {
        }

        override fun onAnimationStart(animation: com.nineoldandroids.animation.Animator?) {
        }

    }

    private fun animateCategoriesStrip() {
        anim = YoYo.with(Techniques.FlipInX)
            .withListener(listener)
            .duration(Constants.SPLASH_TIME_OUT.toLong())
            .playOn(splashLogo)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        backPressed = true
        anim?.stop(true)
    }
}
