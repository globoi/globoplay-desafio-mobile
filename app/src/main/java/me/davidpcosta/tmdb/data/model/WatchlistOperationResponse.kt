package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class WatchlistOperationResponse (
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("status_message") val statusMessage: String
)