package br.com.details_movie.presentation

import br.com.details_movie.domain.model.Movie


sealed interface MovieUiState {

    data class Success(
        val movie: Movie?,
    ) : MovieUiState

    object Empty : MovieUiState
}