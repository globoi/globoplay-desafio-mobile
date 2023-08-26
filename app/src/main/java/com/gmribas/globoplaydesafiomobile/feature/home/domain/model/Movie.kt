package com.gmribas.globoplaydesafiomobile.feature.home.domain.model

data class Movie (
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)