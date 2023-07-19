package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesScreenSuccessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface MoviesViewModel {
    val loadingState: StateFlow<Boolean>
    val uiState: StateFlow<UiState>

    val searchQuery: MutableStateFlow<String>
    val searchModeEnable: MutableStateFlow<Boolean>

    fun loadMovies()
    fun toogleFilterByFavorites()
    fun loadMoviesDelayed()

    sealed class UiState {
        data class Success(val successState: MoviesScreenSuccessState) : UiState()
        data class Error(val exception: Exception, val message: String) : UiState()
        object Empty : UiState()
    }
}