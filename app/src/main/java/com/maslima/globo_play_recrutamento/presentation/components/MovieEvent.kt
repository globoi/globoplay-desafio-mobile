package com.maslima.globo_play_recrutamento.presentation.components

import com.maslima.globo_play_recrutamento.domain.model.Movie

sealed class MovieEvent {
    data class GetMovieEvent(
        val id: Int
    ) : MovieEvent()
    object AddMovieEvent : MovieEvent()
    object DeleteMovieEvent : MovieEvent()
    object SelectSpecificMovie : MovieEvent()
}