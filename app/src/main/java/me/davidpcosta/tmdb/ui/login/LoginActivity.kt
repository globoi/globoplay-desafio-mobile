package me.davidpcosta.tmdb.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.databinding.ActivityLoginBinding
import me.davidpcosta.tmdb.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
            this.viewModel = loginViewModel
            this.activity = this@LoginActivity
        }

        startObservers()
    }

    fun login () {
        loginViewModel.login()
    }


    private fun startObservers() {
        startLoginResultObserver()
    }

    private fun startLoginResultObserver() {
        loginViewModel.loginResult.observe(this, Observer {
            if (it.success) {
                toast("Login OK " + it.requestToken)
            }
            else {
                toast("Erro")
            }
        })
    }
}
