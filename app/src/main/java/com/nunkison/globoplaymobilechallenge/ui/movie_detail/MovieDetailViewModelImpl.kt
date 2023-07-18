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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModelImpl(
    id: String,
    private val repo: MoviesRepository
) : ViewModel(), MovieDetailViewModel {
    private val _loadingState = MutableStateFlow(true)
    override val loadingState: StateFlow<Boolean> = _loadingState

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Success(null))
    override val uiState: StateFlow<UiState> = _uiState

    init {
        loadMovie(id)
    }

    override fun loadMovie(id: String) {
        viewModelScope.launch(IO) {
            try {
                _uiState.emit(
                    UiState.Success(
                        repo.getMovie(id)
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(
                    UiState.Error(
                        e.message ?: stringResource(R.string.generic_error)
                    )
                )
                e.printStackTrace()
            }
            _loadingState.emit(false)
        }
    }

    override fun toogleFavorite() {
        viewModelScope.launch(IO) {
            try {
                (uiState.value as UiState.Success).let {
                    it.data?.let { mdd ->
                        if (mdd.isFavorite) {
                            repo.removeFavorite(it.data.id)
                        } else {
                            repo.addFavorite(it.data.id)
                        }
                        _uiState.update { currentUiState ->
                            if (currentUiState is UiState.Success) {
                                UiState.Success(
                                    currentUiState.data?.copy(isFavorite = !mdd.isFavorite)
                                )
                            } else {
                                currentUiState
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.emit(
                    UiState.Error(
                        e.message ?: stringResource(R.string.generic_error)
                    )
                )
                e.printStackTrace()
            }
        }
    }
}