package com.ftoniolo.globoplay.framework.network.response.watchtoo

import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.globoplay.BuildConfig
import com.google.gson.annotations.SerializedName

data class WatchTooResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("poster_path")
    val posterPath: String
)

fun WatchTooResponse.toWatchTooModel(): WatchToo {
    return WatchToo(
        id = this.id,
        imageUrl = "${BuildConfig.THUMBNAIL_URL}${this.posterPath}"
    )
}

