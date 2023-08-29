package com.gmribas.globoplaydesafiomobile.feature.details.domain.model

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String,
    val homepage: String,
    val id: Int,
    val imdbID: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val status: String,
    val title: String,
    val video: Boolean,
    val spokenLanguages: List<Language>
)