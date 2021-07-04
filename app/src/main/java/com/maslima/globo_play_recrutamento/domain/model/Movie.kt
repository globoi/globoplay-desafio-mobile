package com.maslima.globo_play_recrutamento.domain.model

import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieCategory

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<MovieCategory>,
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
    val voteCount: Int
)