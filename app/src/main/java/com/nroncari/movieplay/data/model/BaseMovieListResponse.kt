package com.nroncari.movieplay.data.model

class BaseMovieListResponse<T> {
    var page = 1
    var results: ArrayList<MovieListItemResponse>? = null
    var totalPages = 0
    var totalResults = 0
}