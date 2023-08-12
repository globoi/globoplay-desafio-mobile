package br.com.details_movie.presentation

sealed interface UiState<out T> {
    data class Success<T> (val data: T?) : UiState<T>
    data class Error(val message: String?) : UiState<Nothing>
    object Loading : UiState<Nothing>
    object Empty : UiState<Nothing>

}
