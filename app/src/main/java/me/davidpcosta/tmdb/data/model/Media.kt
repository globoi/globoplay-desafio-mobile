package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class Media (
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("media_id") val mediaId: Long,
    @SerializedName("watchlist") val watchlist: Boolean
)