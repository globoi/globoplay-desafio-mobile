package com.com.globo.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.com.globo.R
import com.com.globo.main.MainActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        disposable = Observable
            .timer(3, TimeUnit.SECONDS)
            .subscribe {
                startActivity(Intent(this, MainActivity::class.java))
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}