package br.com.nerdrapido.themoviedbapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.di.KoinManager
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.home.HomeActivity
import br.com.nerdrapido.themoviedbapp.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class SplashScreenActivity: AbstractActivity<SplashScreenView, SplashScreenPresenter>(), SplashScreenView {

    override  val presenter: SplashScreenPresenter by inject()

    override  val layoutId = R.layout.activity_splash_screen

    override fun getActivityTitle(): String {
        return getString(R.string.splash_title)
    }

    override fun onResume() {
        super.onResume()
        //Use of delayed call of end of the splash screen for splashscreen demo purposes
        val handler = Handler()
        handler.postDelayed(Runnable {
            presenter.endOfSplashScreen()
        }, 1000)
    }

    override fun goHome() {
        val newIntent = Intent(this, HomeActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
        this.finish()
    }

    override fun goLogin() {
        val newIntent = Intent(this, LoginActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
        this.finish()
    }


}