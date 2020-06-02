package me.davidpcosta.tmdb.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.AccountDetails
import me.davidpcosta.tmdb.data.model.AuthenticationResult
import me.davidpcosta.tmdb.data.model.LoginResponse
import me.davidpcosta.tmdb.data.model.SessionResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(private val api: Api) {

    fun validateLogin(username: String, password: String): LiveData<LoginResponse> {
        val authenticationResult = MutableLiveData<LoginResponse>()
        val loginResponse = LoginResponse()

        api.createRequestToken().enqueue(object: Callback<AuthenticationResult>{
            override fun onResponse(call: Call<AuthenticationResult>, response: Response<AuthenticationResult>) {

                if(response.code() == 401 || response.code() == 404) {
                    loginResponse.success = false
                    loginResponse.errorMessage = "Erro ao fazer login. Tente novamente."
                    authenticationResult.value = loginResponse
                    return
                }

                val requestTokenResult = response.body()
                if (requestTokenResult != null) {

                    // Validar login e senha
                    api.validateWithLogin(username, password, requestTokenResult.requestToken).enqueue(object: Callback<AuthenticationResult>{
                        override fun onResponse(call: Call<AuthenticationResult>, response: Response<AuthenticationResult>) {

                            if(response.code() == 401 || response.code() == 404) {
                                loginResponse.success = false
                                loginResponse.errorMessage = "Login ou senha inválidos"
                                authenticationResult.value = loginResponse
                                return
                            }

                            val loginResult = response.body()
                            if (loginResult != null && loginResult.success) {

                                // Cria session id
                                api.createSession(requestTokenResult.requestToken).enqueue(object: Callback<SessionResult> {
                                    override fun onResponse(call: Call<SessionResult>, response: Response<SessionResult>) {

                                        if(response.code() == 401 || response.code() == 404) {
                                            loginResponse.success = false
                                            loginResponse.errorMessage = "Erro ao fazer login. Tente novamente."
                                            authenticationResult.value = loginResponse
                                            return
                                        }

                                        val sessionResult = response.body()
                                        if (sessionResult != null && loginResult.success) {
                                            loginResponse.sessionId = sessionResult.sessionId

                                            // Obtem account id
                                            api.accountDetails(sessionResult.sessionId).enqueue(object: Callback<AccountDetails> {
                                                override fun onResponse(call: Call<AccountDetails>, response: Response<AccountDetails>) {

                                                    if(response.code() == 401 || response.code() == 404) {
                                                        loginResponse.success = false
                                                        loginResponse.errorMessage = "Erro ao fazer login. Tente novamente."
                                                        authenticationResult.value = loginResponse
                                                        return
                                                    }

                                                    val accountDetails = response.body()
                                                    if (accountDetails != null) {
                                                        loginResponse.success = true
                                                        loginResponse.accountId = accountDetails.id
                                                        loginResponse.sessionId = sessionResult.sessionId
                                                        authenticationResult.value = loginResponse
                                                    }
                                                }
                                                override fun onFailure(call: Call<AccountDetails>, t: Throwable) {
                                                    loginResponse.success = false
                                                    loginResponse.errorMessage = t.message
                                                    authenticationResult.value = loginResponse
                                                    return
                                                }
                                            })
                                        }
                                    }
                                    override fun onFailure(call: Call<SessionResult>, t: Throwable) {
                                        loginResponse.success = false
                                        loginResponse.errorMessage = t.message
                                        authenticationResult.value = loginResponse
                                        return
                                    }
                                })
                            }
                        }
                        override fun onFailure(call: Call<AuthenticationResult>, t: Throwable) {
                            loginResponse.success = false
                            loginResponse.errorMessage = t.message
                            authenticationResult.value = loginResponse
                            return
                        }
                    })
                }
            }
            override fun onFailure(call: Call<AuthenticationResult>, t: Throwable) {
                loginResponse.success = false
                loginResponse.errorMessage = t.message
                authenticationResult.value = loginResponse
                return
            }
        })

        return authenticationResult
    }

    fun accountDetails(sessionId: String): LiveData<AccountDetails> {
        val accountDetails = MutableLiveData<AccountDetails>()

        api.accountDetails(sessionId).enqueue(object: Callback<AccountDetails>{
            override fun onResponse(call: Call<AccountDetails>, response: Response<AccountDetails>) {
                accountDetails.value = response.body()
            }
            override fun onFailure(call: Call<AccountDetails>, t: Throwable) {
                throw t
            }
        })

        return accountDetails
    }
}
