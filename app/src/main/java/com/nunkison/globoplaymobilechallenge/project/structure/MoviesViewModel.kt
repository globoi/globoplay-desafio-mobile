package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup
import kotlinx.coroutines.flow.StateFlow

interface MoviesViewModel {
    val loadingState: StateFlow<Boolean>
    val uiState: StateFlow<UiState>

    fun loadMovies()

    sealed class UiState {
        data class Success(val data: List<MoviesGroup>) : UiState()
        data class Error(val message: String) : UiState()
    }
}