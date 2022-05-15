package com.simonassi.globoplay.data.movie

import com.google.gson.annotations.SerializedName
import com.simonassi.globoplay.data.movie.Movie

data class MovieSearchResponse(
    @field:SerializedName("results") val results: List<Movie>,
    @field:SerializedName("total_pages") val totalPages: Int
)