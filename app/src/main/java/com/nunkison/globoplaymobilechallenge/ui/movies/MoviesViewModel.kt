package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.lifecycle.ViewModel
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup
import com.nunkison.globoplaymobilechallenge.ui.movies.data.moviesDataMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesViewModel: ViewModel() {
    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState = MutableStateFlow(UiState.Success(moviesDataMock))
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        data class Success(val data: List<MoviesGroup>) : UiState()
        data class Error(val message: String) : UiState()
    }
}