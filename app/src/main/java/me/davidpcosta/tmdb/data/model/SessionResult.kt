package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class SessionResult(
        @SerializedName("success")val success: Boolean,
        @SerializedName("session_id") val sessionId: String
)
