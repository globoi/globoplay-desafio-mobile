package com.example.globechallenge.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.globechallenge.MainActivity
import com.example.globechallenge.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            showHomeScreen()}, 4000)
        binding.splashImage.animate().apply {
            duration = 1500
            translationYBy(360f)
            alpha(0.5f)
        }.withEndAction {
            binding.splashImage.animate().apply {
                duration = 1500
                alpha(1f)
                translationYBy(360f)
            }
        }
    }

    private fun showHomeScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}