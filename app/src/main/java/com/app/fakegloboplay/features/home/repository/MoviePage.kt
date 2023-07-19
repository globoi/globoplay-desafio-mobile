package com.app.fakegloboplay.features.home.repository

import com.app.fakegloboplay.network.response.Movie

data class MovieListPage(
    var page: Int = 0,
    var results: List<Movie> = listOf(),
    var totalPage: Int = 0
) {
    fun nextPage(): Int {
        return if (page < totalPage) {
            page + 1
        } else {
            page
        }
    }
}