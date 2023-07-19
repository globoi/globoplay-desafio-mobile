package com.app.fakegloboplay.features.commons

import com.app.fakegloboplay.features.home.repository.MovieListPage
import com.app.fakegloboplay.network.response.Movie

sealed class ListMoviePageViewState {
    data class Success(val listMovie: MovieListPage): ListMoviePageViewState()
    object Empty: ListMoviePageViewState()
    object Error : ListMoviePageViewState()
}

sealed class ListMovieViewState {
    data class Success(val listMovie: List<Movie>): ListMovieViewState()
    object Empty: ListMovieViewState()
    object Error : ListMovieViewState()
}