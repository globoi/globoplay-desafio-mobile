package com.example.globechallenge.ui.details.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globechallenge.data.model.models.details.MovieCast
import com.example.globechallenge.data.model.models.details.MovieDetails
import com.example.globechallenge.data.model.models.details.MovieVideos
import com.example.globechallenge.data.repository.details.MovieDetailsRepositoryImplementation
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repositoryImplementation: MovieDetailsRepositoryImplementation) : ViewModel(){

    val movieDetailLiveData = MutableLiveData<MovieDetails>()
    val movieCastMutableLiveData = MutableLiveData<MovieCast>()
    val movieVideoMutableLivedata = MutableLiveData<MovieVideos>()

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movieDetailLiveData.postValue(repositoryImplementation.getMovieDetail(id))
        }
    }

    fun getMovieCreditToGetCast(id: String) {
        viewModelScope.launch {
            movieCastMutableLiveData.postValue(repositoryImplementation.getMovieCreditToGetCast(id))
        }
    }

    fun getMovieVideos(id: String) {
        viewModelScope.launch {
            movieVideoMutableLivedata.postValue(repositoryImplementation.getMovieVideos(id))
        }
    }

    class MovieDetailViewModelFactory(private val repositoryImplementation: MovieDetailsRepositoryImplementation) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel(repositoryImplementation) as T
        }
    }
}