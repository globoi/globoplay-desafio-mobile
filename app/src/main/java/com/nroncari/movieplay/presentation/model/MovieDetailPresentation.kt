package com.nroncari.movieplay.presentation.model

class MovieDetailPresentation(
    val id: Long = 0,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    var backdropPath: String,
    val average: Float = 0.0.toFloat(),
)