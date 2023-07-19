package com.nunkison.globoplaymobilechallenge.project.api

class MovieListResponse(
    val results: List<DiscoverMovie>
) {
    class DiscoverMovie(
        val id: String,
        val title: String,
        val poster_path: String?
    )
}