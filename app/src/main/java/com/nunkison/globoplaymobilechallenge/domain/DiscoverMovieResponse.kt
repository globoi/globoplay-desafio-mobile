package com.nunkison.globoplaymobilechallenge.domain

class DiscoverMovieResponse(
    val results: List<DiscoverMovie>
) {
    class DiscoverMovie(
        val id: String,
        val title: String,
        val poster_path: String
    )
}