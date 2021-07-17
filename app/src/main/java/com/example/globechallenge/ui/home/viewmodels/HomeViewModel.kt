package com.example.globechallenge.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.globechallenge.data.model.MovieToGenre
import com.example.globechallenge.data.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    val movieByGenreLiveData = MutableLiveData<List<MovieToGenre>>()

    fun getMovieByGenre() {
        viewModelScope.launch {
            movieByGenreLiveData.postValue(repository.getMovieByGenre())
        }
    }

    class HomeViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}