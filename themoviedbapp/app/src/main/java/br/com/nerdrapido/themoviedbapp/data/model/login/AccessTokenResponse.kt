package br.com.nerdrapido.themoviedbapp.data.model.login

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
data class AccessTokenResponse (
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("account_id")
    val accountId: String
)