package com.globo.moviesapp.ui.account.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globo.moviesapp.network.account.AccountRepository
import java.lang.Exception
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    var apiKey: String? = null
    var error= MutableLiveData<String>()

    fun getSessionIdAndAccountId(): Boolean {
        try {
            val requestToken = repository.getRequestToken(apiKey!!)
            val requestTokenSession = repository.getRequestTokenSession(apiKey!!, requestToken)
            val sessionId = repository.getSessionId(apiKey!!, requestTokenSession)
            val accountId = repository.getAccountId(apiKey!!, sessionId)
            with(sharedPreferences.edit()){
                putString("SESSION_ID", sessionId)
                putInt("ACCOUNT_ID", accountId)
                commit()
            }
            return true
        } catch (e: Exception){
            error.postValue(e.message)
        }
        return false
    }
}