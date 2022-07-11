package com.nroncari.movieplay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.nroncari.movieplay.domain.mapper.MovieToPresentationMapper
import com.nroncari.movieplay.domain.usecase.GetMoviesByKeywordUseCase
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val useCase: GetMoviesByKeywordUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _movies = MutableLiveData<PagingData<MovieListItemPresentation>>()
    val movies: LiveData<PagingData<MovieListItemPresentation>> get() = _movies

    fun searchMovies(keyword: String) {
        viewModelScope.launch(dispatcher) {
            useCase(keyword).cachedIn(viewModelScope).collect { movies ->
                _movies.postValue(movies.map { MovieToPresentationMapper().map(it) })
            }
        }
    }
}