package me.davidpcosta.tmdb.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.davidpcosta.tmdb.data.model.AccountDetails
import me.davidpcosta.tmdb.data.repository.AccountRepository
import me.davidpcosta.tmdb.data.model.AuthenticationResult
import me.davidpcosta.tmdb.data.model.SessionResult


class LoginViewModel(private val loginRepository: AccountRepository) : ViewModel() {

    var username: String = "davidpcosta"
    var password: String = "1234qwer"

    lateinit var requestToken: String

    val errorMessage: LiveData<String> = MutableLiveData()
    val sessionResult: LiveData<SessionResult> = MutableLiveData()
    val accountDetails: LiveData<AccountDetails> = MutableLiveData()


    fun validateLogin() {
        createRequestToken()
    }

    private fun login() {
        loginRepository.login(username, password, requestToken).subscribe({
            if (it.success) {
                createSession()
            } else {
                errorMessage as MutableLiveData
                errorMessage.value = "Login ou senha incorretos"
            }
        },
            { e ->
                e.printStackTrace()
                errorMessage as MutableLiveData
                errorMessage.value = e.message
            })
    }

    private fun createRequestToken() {
        loginRepository.createRequestToken().subscribe ({
            if (it.success) {
                requestToken = it.requestToken
                login()
            } else {
                errorMessage as MutableLiveData
                errorMessage.value = "Erro gerando token"
            }
        },
            { e ->
                e.printStackTrace()
                errorMessage as MutableLiveData
                errorMessage.value = e.message
            })
    }

    private fun createSession() {
        loginRepository.createSession(requestToken).subscribe({
            if (it.success) {
                sessionResult as MutableLiveData
                sessionResult.value = it
                getAccountDetails()
            } else {
                errorMessage as MutableLiveData
                errorMessage.value = "Sessão inválida"
            }
        },
            { e ->
                e.printStackTrace()
                errorMessage as MutableLiveData
                errorMessage.value = e.message
            })
    }

    private fun getAccountDetails() {
        loginRepository.accountDetails(sessionResult.value!!.sessionId).subscribe({
            accountDetails as MutableLiveData
            accountDetails.value = it
        },
            { e ->
                e.printStackTrace()
                errorMessage as MutableLiveData
                errorMessage.value = e.message
            })
    }
}
