package com.nroncari.movieplay.data.model

import com.google.gson.annotations.SerializedName

data class MovieListItemResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)
