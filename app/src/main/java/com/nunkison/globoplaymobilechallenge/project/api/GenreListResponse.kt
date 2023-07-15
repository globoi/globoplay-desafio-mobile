package com.nunkison.globoplaymobilechallenge.project.api

class GenreListResponse(
    val genres: List<Genre>
) {
    class Genre(
        val id: String,
        val name: String,
    )
}