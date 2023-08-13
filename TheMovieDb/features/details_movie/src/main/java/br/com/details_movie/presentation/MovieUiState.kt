package br.com.details_movie.presentation

import br.com.details_movie.domain.model.MovieDetails


sealed interface MovieUiState {

    data class Success(
        val movieDetails: MovieDetails?,
    ) : MovieUiState

    object Empty : MovieUiState
}