package com.simonassi.globoplay.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.simonassi.globoplay.databinding.ActivitySplashScreenBinding
import com.simonassi.globoplay.ui.main.MainActivity
import java.util.Timer
import kotlin.concurrent.schedule


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val zoomIn: Animation = ScaleAnimation(
            1f, 1.1f,
            1f, 1.1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        zoomIn.duration = ZOOM_IN_DURATION

        val fadeOut = AlphaAnimation(1f, 0.0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.startOffset = START_FADE_OUT_OFFSET
        fadeOut.duration = FADE_OUT_DURATION

        val animation = AnimationSet(false)
        animation.addAnimation(zoomIn)
        animation.addAnimation(fadeOut)
        animation.fillAfter = true

        Timer("Navigate", false).schedule(1500) {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.splashIconImageView.animation = animation
    }

    companion object{
        const val ZOOM_IN_DURATION = 2000L
        const val START_FADE_OUT_OFFSET = 800L
        const val FADE_OUT_DURATION = 800L
    }
}