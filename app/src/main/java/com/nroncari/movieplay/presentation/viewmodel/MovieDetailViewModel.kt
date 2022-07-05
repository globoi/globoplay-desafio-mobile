package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.nroncari.movieplay.domain.mapper.MovieToPresentationMapper
import com.nroncari.movieplay.domain.usecase.GetMovieDetailUseCase
import com.nroncari.movieplay.domain.usecase.GetMovieRecommendationsUseCase
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val recommendationsUseCase: GetMovieRecommendationsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _onRequisitionError = MutableLiveData<String?>()
    val onRequisitionError: LiveData<String?> get() = _onRequisitionError

    private val _movie = MutableLiveData<MovieDetailPresentation>().apply { null }
    val movie: LiveData<MovieDetailPresentation> get() = _movie

    private val _movieOriginalTitle = MutableLiveData<String?>()
    val movieOriginalTitle: LiveData<String?> get() = _movieOriginalTitle

    private val _movieYear = MutableLiveData<String?>()
    val movieYear: LiveData<String?> get() = _movieYear

    private val _average = MutableLiveData<Float?>()
    val average: LiveData<Float?> get() = _average

    private val _listRecommendations =
        MutableLiveData<PagingData<MovieListItemPresentation>>()
    val listRecommendations: MutableLiveData<PagingData<MovieListItemPresentation>> get() = _listRecommendations

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch(dispatcher) {

            kotlin.runCatching {
                getMovieDetailUseCase(movieId)
            }.onSuccess {
                _movie.postValue(it)
                _movieOriginalTitle.postValue(it.originalTitle)
                _movieYear.postValue(it.releaseDate)
                _average.postValue(it.average / 2)
            }.onFailure {
                _onRequisitionError.postValue(it.message)
            }
        }
        getMoviesRecommended(movieId)
    }

    private fun getMoviesRecommended(movieId: Long) {
        viewModelScope.launch(dispatcher) {

            recommendationsUseCase(movieId).cachedIn(viewModelScope).collect { movies ->
                _listRecommendations.postValue(movies.map { MovieToPresentationMapper().map(it) })
            }
        }
    }
}