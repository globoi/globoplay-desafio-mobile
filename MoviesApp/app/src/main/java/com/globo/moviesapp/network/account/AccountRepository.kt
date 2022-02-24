package com.globo.moviesapp.network.account

import com.globo.moviesapp.model.accountRequestTokenSession.AccountRequestTokenSession
import com.google.gson.Gson
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class AccountRepository @Inject constructor(
    val retrofit: Retrofit
) {
    fun getAccountId(apiKey: String, sessionId: String): Int {
        val call = retrofit.create(AccountServiceInterface::class.java)
            .getAccountIdCall(apiKey, sessionId).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result?.get("id"), Int::class.java)
    }

    fun getSessionId(apiKey: String, requestToken: String): String {
        val accountUserSession = AccountRequestTokenSession("", "", requestToken)

        val call = retrofit.create(AccountServiceInterface::class.java)
            .getSessionIdCall(apiKey, accountUserSession).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result?.get("session_id"), String::class.java)
    }

    fun getRequestTokenSession(apiKey: String, requestToken: String): String {
        val accountUserSession = AccountRequestTokenSession("andrefellype", "andre0311",
            requestToken)

        val call = retrofit.create(AccountServiceInterface::class.java)
            .getRequestTokenSessionCall(apiKey, accountUserSession).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result?.get("request_token"), String::class.java)
    }

    fun getRequestToken(apiKey: String): String {
        val call = retrofit.create(AccountServiceInterface::class.java)
            .getRequestTokenCall(apiKey).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result?.get("request_token"), String::class.java)
    }
}