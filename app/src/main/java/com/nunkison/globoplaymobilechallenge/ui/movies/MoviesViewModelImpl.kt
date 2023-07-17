package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel.UiState
import com.nunkison.globoplaymobilechallenge.stringResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModelImpl(
    private val repo: MoviesRepository
) : ViewModel(), MoviesViewModel {
    private val _loadingState = MutableStateFlow(true)
    override val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState.Success(arrayListOf())
    )
    override val uiState: StateFlow<UiState> = _uiState

    init {
        loadMovies()
    }

    override fun loadMovies() {
        viewModelScope.launch(IO) {
            try {
                _uiState.emit(
                    UiState.Success(
                        repo.getMovies()
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(
                    UiState.Error(
                        e.message ?: stringResource(R.string.generic_error)
                    )
                )
            }
            _loadingState.value = false
        }
    }
}