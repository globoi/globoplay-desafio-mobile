package br.com.nerdrapido.themoviedbapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class SplashScreenActivity: AbstractActivity<SplashScreenPresenter>(), SplashScreenView {

    override  val presenter: SplashScreenPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }

    override fun onResume() {
        super.onResume()
        //Use of delayed call of end of the splash screen for splashscreen demo purposes
        val handler = Handler()
        handler.postDelayed(Runnable {
            // Actions to do after 10 seconds
        }, 10000)
    }

    override fun goHome() {
        TODO("Not yet implemented")
    }

    override fun goLogin() {
        val newIntent = Intent(this, LoginActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
    }
}