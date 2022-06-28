package com.nroncari.movieplay.presentation.model

class MovieDetailPresentation(
    val id: Int = 0,
    val budget: Int = 0,
    val homepage: String,
    val imdbId: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double = 0.0,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)