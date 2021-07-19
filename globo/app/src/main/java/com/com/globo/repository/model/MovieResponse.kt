package com.com.globo.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("total_results")
    @Expose(serialize = false)
    var totalResults: Int = 0,

    @SerializedName("results")
    @Expose(serialize = false)
    var listMovies: List<Movie>?
)
