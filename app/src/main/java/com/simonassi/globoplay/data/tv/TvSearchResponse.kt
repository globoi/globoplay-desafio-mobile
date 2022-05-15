package com.simonassi.globoplay.data.tv

import com.google.gson.annotations.SerializedName
import com.simonassi.globoplay.data.tv.Tv

data class TvSearchResponse (
    @field:SerializedName("results") val results: List<Tv>,
    @field:SerializedName("total_pages") val totalPages: Int
)