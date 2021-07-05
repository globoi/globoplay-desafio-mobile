package com.maslima.globo_play_recrutamento.presentation.components

sealed class MovieEvent {
    data class GetMovieEvent(
        val id: Int
    ) : MovieEvent()
}