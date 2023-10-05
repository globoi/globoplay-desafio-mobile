package com.mazer.globoplayapp.presentation.ui.main.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import kotlinx.coroutines.launch

class MyListViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _myFavoritesList = MutableLiveData<List<Movie>>()
    var myFavoritesList: LiveData<List<Movie>> = _myFavoritesList

    init {
        loadFavoriteList()
    }

    fun loadFavoriteList() {
        viewModelScope.launch {
            myFavoritesList = getMovieListUseCase.getAllFavorites()
        }
    }
}