package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.ui.movie_detail.data.MovieDetailData
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailViewModel {
    val loadingState: StateFlow<Boolean>
    val uiState: StateFlow<UiState>
    fun loadMovie(id: String)
    fun setTabIndex(index: Int)

    sealed class UiState {
        data class Success(val data: MovieDetailData?) : UiState()
        data class Error(val message: String) : UiState()
    }
}