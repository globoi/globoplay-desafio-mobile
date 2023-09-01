package com.mazer.globoplayapp.presentation.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _moviePopularList = MutableLiveData<List<Movie>>()
    val moviePopularList: LiveData<List<Movie>> = _moviePopularList

    private val _movieTopRatedList = MutableLiveData<List<Movie>>()
    val movieTopRatedList: LiveData<List<Movie>> = _movieTopRatedList

    private val _movieUpcomingList = MutableLiveData<List<Movie>>()
    val movieUpcomingList: LiveData<List<Movie>> = _movieUpcomingList

    init {
        loadPopularMovies()
        loadTopRatedMovies()
        loadUpcomingMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getPopularMovies()
            _moviePopularList.postValue(movies)
        }
    }

    private fun loadTopRatedMovies() {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getTopRatedMovies()
            _movieTopRatedList.postValue(movies)
        }
    }

    private fun loadUpcomingMovies() {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getUpcomingMovies()
            _movieUpcomingList.postValue(movies)
        }
    }

}