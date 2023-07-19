package com.app.fakegloboplay.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock.sleep
import androidx.appcompat.app.AppCompatActivity
import com.app.fakegloboplay.R
import com.app.fakegloboplay.features.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayedSplashScreen(600)
    }

    private fun delayedSplashScreen(delayMillis: Long) {
        Thread {
            sleep(delayMillis)
            showHome()
        }.start()
    }

    private fun showHome() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}