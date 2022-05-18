package com.simonassi.globoplay.data.movie

import com.google.gson.annotations.SerializedName
import com.simonassi.globoplay.data.VideoKey

data class MovieVideoResponse (
    @field:SerializedName("results") val results: List<VideoKey>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("page") val page: Int,
)