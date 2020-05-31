package me.davidpcosta.tmdb.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.*
import me.davidpcosta.tmdb.ui.main.MainActivity
import me.davidpcosta.tmdb.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loading: ProgressBar
    private lateinit var loginButton: Button
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        sharedPreferences = getSharedPreferences("sessionId", Context.MODE_PRIVATE)

        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).apply {
            this.viewModel = loginViewModel
            this.activity = this@LoginActivity
        }

        loading = findViewById(R.id.loading)
        loginButton = findViewById(R.id.login_button)

        observeSessionResult()
        observeErrorMessage()
    }

    fun handleLoginClick () {
        loading.show()
        loginButton.disable()
        loginViewModel.validateLogin()
    }

    private fun observeErrorMessage() {
        loginViewModel.errorMessage.observe(this, Observer {
            loading.hide()
            loginButton.enable()
            toast(it)
        })
    }

    private fun observeSessionResult() {
        loginViewModel.sessionResult.observe(this, Observer {
            if (it.success) {
                loading.hide()
                loginButton.enable()
                saveSessionId()
                goToMainActivity()
            }
        })
    }

    private fun saveSessionId() {
        loginViewModel.sessionResult.value?.let {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("sessionId", it.sessionId)
            editor.apply()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
