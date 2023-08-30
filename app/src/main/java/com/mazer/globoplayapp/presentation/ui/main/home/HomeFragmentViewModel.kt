package com.mazer.globoplayapp.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    init {
       // loadPopularMovies()
    }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

     fun loadPopularMovies() {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getPopularMovies()
            _movieList.postValue(movies)
        }
    }

}