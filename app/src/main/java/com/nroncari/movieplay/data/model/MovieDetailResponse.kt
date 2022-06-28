package com.nroncari.movieplay.data.model

data class MovieDetailResponse(
    val id: Int? = 0,
    val budget: Int = 0,
    val homepage: String? = null,
    val imdbId: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
) 
