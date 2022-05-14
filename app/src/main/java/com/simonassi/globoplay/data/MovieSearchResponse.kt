package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @field:SerializedName("results") val results: List<Movie>,
    @field:SerializedName("total_pages") val totalPages: Int
)