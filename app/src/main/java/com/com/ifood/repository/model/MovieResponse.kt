package com.com.ifood.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("total_pages")
    @Expose(serialize = false)
    var totalPages: Int = 0,

    @SerializedName("results")
    @Expose(serialize = false)
    var listMovies: MutableList<Movie>?,

    var nextPageToLoad: Int
)
