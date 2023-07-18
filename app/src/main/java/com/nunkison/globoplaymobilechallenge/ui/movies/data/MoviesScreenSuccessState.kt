package com.nunkison.globoplaymobilechallenge.ui.movies.data

data class MoviesScreenSuccessState(
    val favoriteFilterEnable: Boolean,
    val data: List<MoviesGroup>
)