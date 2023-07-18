package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesScreenSuccessState
import kotlinx.coroutines.flow.StateFlow

interface MoviesViewModel {
    val loadingState: StateFlow<Boolean>
    val uiState: StateFlow<UiState>

    fun loadMovies()
    fun toogleFilterByFavorites()

    sealed class UiState {
        data class Success(val successState: MoviesScreenSuccessState) : UiState()
        data class Error(val message: String) : UiState()
    }
}