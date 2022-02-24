package com.globo.moviesapp.network.account

import com.globo.moviesapp.model.accountRequestTokenSession.AccountRequestTokenSession
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AccountServiceInterface {
    @GET("/3/account")
    fun getAccountIdCall(@Query("api_key") apiKey: String, @Query("session_id") sessionId: String): Call<JsonObject>

    @POST("/3/authentication/session/new")
    fun getSessionIdCall(@Query("api_key") apiKey: String, @Body accountRequestTokenSession: AccountRequestTokenSession): Call<JsonObject>

    @POST("/3/authentication/token/validate_with_login")
    fun getRequestTokenSessionCall(@Query("api_key") apiKey: String, @Body accountRequestTokenSession: AccountRequestTokenSession): Call<JsonObject>

    @GET("/3/authentication/token/new")
    fun getRequestTokenCall(@Query("api_key") apiKey: String): Call<JsonObject>
}