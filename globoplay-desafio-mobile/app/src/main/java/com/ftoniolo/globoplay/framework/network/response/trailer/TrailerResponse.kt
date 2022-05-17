package com.ftoniolo.globoplay.framework.network.response.trailer

import com.ftoniolo.core.domain.model.Trailer
import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("key")
    val key: String
)

fun TrailerResponse.toTrailer(): Trailer {
    return Trailer(
        trailerUrl = "https://www.youtube.com/watch?v=${key}"
    )
}