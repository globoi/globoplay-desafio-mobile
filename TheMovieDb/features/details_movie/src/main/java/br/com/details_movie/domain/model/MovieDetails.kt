package br.com.details_movie.domain.model

data class MovieDetails(
    val id: Int,
    val overview: String,
    val tagline: String,
    val posterPath: String,
    val title: String,
    val subtitle: String,
)