package com.simonassi.globoplay.data.tv

import com.google.gson.annotations.SerializedName
import com.simonassi.globoplay.data.VideoKey

data class TvVideoResponse (
    @field:SerializedName("results") val results: List<VideoKey>,
    )