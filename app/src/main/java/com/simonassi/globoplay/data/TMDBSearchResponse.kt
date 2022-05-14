package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class TMDBSearchResponse(
    @field:SerializedName("results") val results: List<TMDBMovie>,
    @field:SerializedName("total_pages") val totalPages: Int
)