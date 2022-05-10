package com.ftoniolo.globoplay.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.ActivityPresentationScreenBinding
import com.ftoniolo.globoplay.presentation.MainActivity

class PresentationScreenActivity : AppCompatActivity() {

    private lateinit var topAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPresentationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        val splash = binding.logoSplashScreen
        splash.animation = topAnim

        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, DURATION_SPLASH)
    }

    companion object{
        private const val DURATION_SPLASH = 5000L
    }
}