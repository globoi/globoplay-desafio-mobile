package me.davidpcosta.tmdb.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.data.api.ApiService
import me.davidpcosta.tmdb.data.repository.AuthenticationRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    loginRepository = AuthenticationRepository(
                        api = ApiService.instance
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
