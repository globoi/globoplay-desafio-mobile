package com.nunkison.globoplaymobilechallenge.ui.movie_detail.data

import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover

data class MovieDetailData(
    val name: String,
    val coverPath: String,
    val category: String,
    val description: String,
    val isFavorite: Boolean,
    val year: String,
    val country: String,
    val producer: String,
    val youtubeKey: String,
    val relatedMovies: List<MovieCover>,
    val tabSelected: Int,
)