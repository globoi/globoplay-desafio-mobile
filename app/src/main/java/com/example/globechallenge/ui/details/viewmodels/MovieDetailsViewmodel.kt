package com.example.globechallenge.ui.details.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globechallenge.data.model.features.details.MovieCast
import com.example.globechallenge.data.model.features.details.MovieDetails
import com.example.globechallenge.data.model.features.details.MovieVideos
import com.example.globechallenge.data.repository.details.MovieDetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel(){

    val movieDetailLiveData = MutableLiveData<MovieDetails>()
    val movieCastMutableLiveData = MutableLiveData<MovieCast>()
    val movieVideoMutableLivedata = MutableLiveData<MovieVideos>()

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieDetailLiveData.postValue(repository.getMovieDetail(id))
        }
    }

    fun getMovieCreditToGetCast(id: String) {
        viewModelScope.launch {
            movieCastMutableLiveData.postValue(repository.getMovieCreditToGetCast(id))
        }
    }

    fun getMovieVideos(id: String) {
        viewModelScope.launch {
            movieVideoMutableLivedata.postValue(repository.getMovieVideos(id))
        }
    }

    class MovieDetailViewModelFactory(private val repository: MovieDetailsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repository) as T
        }
    }
}