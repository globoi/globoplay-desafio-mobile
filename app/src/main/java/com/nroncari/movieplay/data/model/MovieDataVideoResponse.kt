package com.nroncari.movieplay.data.model

import com.google.gson.annotations.SerializedName

data class MovieDataVideoResponse(
    @SerializedName("key") val path: String?,
    @SerializedName("site") val site: String?,
)
