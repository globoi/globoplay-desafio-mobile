package com.globo.moviesapp.ui.splash.activity

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.globo.moviesapp.R
import com.globo.moviesapp.ui.account.viewmodel.AccountViewModel
import com.globo.moviesapp.ui.panel.activity.PanelActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val accountViewModel: AccountViewModel by viewModels {
        viewModelFactory
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tvGloboplay.setTextColor(getColor(R.color.white))

        val globoplayShader: Shader = LinearGradient(0.0f,0.0f,
            tvGloboplay.paint.measureText(tvGloboplay.text.toString()), tvGloboplay.textSize,
            intArrayOf(getColor(R.color.colorBeginGloboplay), getColor(R.color.colorEndGloboplay)),
            floatArrayOf(0.5f, 1.0f), Shader.TileMode.CLAMP
        )
        tvGloboplay.paint.shader = globoplayShader

        val activity = this
        CoroutineScope(Dispatchers.IO).launch {
            accountViewModel.apiKey = getString(R.string.api_key_movie_db)
            if(accountViewModel.getSessionIdAndAccountId()) {
                val intent = Intent(activity, PanelActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}