package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class TvSearchResponse (
    @field:SerializedName("results") val results: List<Tv>,
    @field:SerializedName("total_pages") val totalPages: Int
)