package com.mazer.globoplayapp.presentation.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _moviePopularList = MutableLiveData<List<Movie>>()
    val moviePopularList: LiveData<List<Movie>> = _moviePopularList

    private val _movieTopRatedList = MutableLiveData<List<Movie>>()
    val movieTopRatedList: LiveData<List<Movie>> = _movieTopRatedList

    private val _movieUpcomingList = MutableLiveData<List<Movie>>()
    val movieUpcomingList: LiveData<List<Movie>> = _movieUpcomingList

    init {
        loadPopularMovies(1)
        loadTopRatedMovies(1)
        loadUpcomingMovies(1)
    }



    fun loadPopularMovies(page: Int) {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getPopularMovies(page)
            _moviePopularList.postValue(movies)
        }
    }

    fun loadTopRatedMovies(page: Int) {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getTopRatedMovies(page)
            _movieTopRatedList.postValue(movies)
        }
    }

    fun loadUpcomingMovies(page: Int) {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getUpcomingMovies(page)
            _movieUpcomingList.postValue(movies)
        }
    }

}