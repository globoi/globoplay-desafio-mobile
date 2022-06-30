package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nroncari.movieplay.domain.usecase.GetMovieDetailUseCase
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _onRequisitionError = MutableLiveData<String?>()
    val onRequisitionError: LiveData<String?> get() = _onRequisitionError

    private val _movie = MutableLiveData<MovieDetailPresentation>().apply { null }
    val movie: LiveData<MovieDetailPresentation> get() = _movie

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch(dispatcher) {

            kotlin.runCatching {
                getMovieDetailUseCase(movieId)
            }.onSuccess {
                _movie.postValue(it)
            }.onFailure {
                _onRequisitionError.postValue(it.message)
            }
        }
    }
}