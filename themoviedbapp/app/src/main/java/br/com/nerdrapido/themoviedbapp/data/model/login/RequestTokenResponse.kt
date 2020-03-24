package br.com.nerdrapido.themoviedbapp.data.model.login

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
data class RequestTokenResponse(
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("expires_at")
    val expiresAt: String
)