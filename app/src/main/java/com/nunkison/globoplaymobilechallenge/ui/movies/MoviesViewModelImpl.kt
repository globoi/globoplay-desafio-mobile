package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.stringResource
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModelImpl(
    private val repo: MoviesRepository
) : ViewModel(), MoviesViewModel {
    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success(arrayListOf()))
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadMovies()
    }

    override fun loadMovies() {
        viewModelScope.launch(IO) {
            try {
                _uiState.value = UiState.Success(
                    repo.getMovies()
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: stringResource(R.string.generic_error)
                )
            }
            _loadingState.value = false
        }
    }

    sealed class UiState {
        data class Success(val data: List<MoviesGroup>) : UiState()
        data class Error(val message: String) : UiState()
    }
}