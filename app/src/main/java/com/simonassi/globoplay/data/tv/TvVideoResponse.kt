package com.simonassi.globoplay.data.tv

import com.google.gson.annotations.SerializedName
import com.simonassi.globoplay.data.VideoKey

data class TvVideoResponse (
    @field:SerializedName("results") val results: List<VideoKey>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("page") val page: Int,
    )