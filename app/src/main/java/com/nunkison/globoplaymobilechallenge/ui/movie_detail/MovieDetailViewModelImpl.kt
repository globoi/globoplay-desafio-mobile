package com.nunkison.globoplaymobilechallenge.ui.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailViewModel
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailViewModel.UiState
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.stringResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModelImpl(
    private val repo: MoviesRepository
) : ViewModel(), MovieDetailViewModel {
    private val _loadingState = MutableStateFlow(true)
    override val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success(null))
    override val uiState: StateFlow<UiState> = _uiState

    override fun loadMovie(id: String) {
        viewModelScope.launch(IO) {
            try {
                _uiState.value = UiState.Success(
                    repo.getMovie("28")
                )
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: stringResource(R.string.generic_error)
                )
            }
            _loadingState.value = false
        }
    }




}