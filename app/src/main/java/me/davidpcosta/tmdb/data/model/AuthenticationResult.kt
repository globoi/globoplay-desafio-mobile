package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResult(
    @SerializedName("success") var success: Boolean,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("request_token") val requestToken: String
)
