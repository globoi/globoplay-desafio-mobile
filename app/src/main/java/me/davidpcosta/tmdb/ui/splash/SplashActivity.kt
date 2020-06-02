package me.davidpcosta.tmdb.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.ui.login.LoginActivity
import me.davidpcosta.tmdb.ui.main.MainActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(getString(R.string.const_shared_preference), MODE_PRIVATE)

        Observable.timer(800, TimeUnit.MILLISECONDS)
            .map { checkUserLoggedIn() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun checkUserLoggedIn() {
        if (isUserLoggedIn()) {
            goToMain()
        } else {
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isUserLoggedIn(): Boolean {
        val sessionId = sharedPreferences.getString(getString(R.string.const_key_session_id), "")
        val accountId = sharedPreferences.getLong(getString(R.string.const_key_account_id), 0)
        return sessionId!!.isNotBlank() && accountId > 0
    }

}