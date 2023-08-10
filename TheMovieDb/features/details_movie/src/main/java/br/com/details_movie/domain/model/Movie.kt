package br.com.details_movie.domain.model

data class Movie(
    val id: Int,
    val description: String,
    val tagline: String,
    val posterUrl: String,
    val title: String,
    val subtitle: String,
)