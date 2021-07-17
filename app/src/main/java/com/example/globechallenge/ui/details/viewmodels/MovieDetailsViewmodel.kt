package com.example.globechallenge.ui.details.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globechallenge.data.model.MovieDetails
import com.example.globechallenge.data.repository.MovieDetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel(){

    val movieDetail = MutableLiveData<MovieDetails>()

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieDetail.postValue(repository.getMovieDetail(id))
        }
    }

    class MovieDetailViewModelFactory(private val repository: MovieDetailsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(repository) as T
        }
    }
}