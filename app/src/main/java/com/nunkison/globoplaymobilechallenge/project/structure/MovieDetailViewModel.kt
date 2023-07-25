package com.nunkison.globoplaymobilechallenge.project.structure

import kotlinx.coroutines.flow.StateFlow

interface MovieDetailViewModel {
    val loadingState: StateFlow<Boolean>
    val uiState: StateFlow<UiState>
    fun loadMovie(id: String)
    fun toogleFavorite()

    sealed class UiState {
        data class Success(
            val data: MovieDetailData?
        ) : UiState()

        data class Error(
            val exception: Exception,
            val message: String
        ) : UiState()
    }
}