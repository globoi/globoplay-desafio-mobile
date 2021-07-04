package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

sealed class MovieListEvent {
    object NewSearchEvent : MovieListEvent()

    object NextPageEvent : MovieListEvent()

    object RestoreStateEvent: MovieListEvent()
}