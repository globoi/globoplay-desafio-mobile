package com.example.globechallenge.ui.mylist.viewmodel

import androidx.lifecycle.*
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import com.example.globechallenge.data.repository.favorites.MyListRepository
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: MyListRepository) : ViewModel() {

    val allFavoriteMovies: LiveData<List<FavoriteMoviesEntity>> =
        repository.allFavoriteMovies.asLiveData()

    fun getFavoriteMovieById(movieId: String): LiveData<FavoriteMoviesEntity> =
        repository.getFavoriteMovieById(movieId).asLiveData()

    fun insert(favoriteMoviesEntity: FavoriteMoviesEntity) = viewModelScope.launch {
        repository.insert(favoriteMoviesEntity)
    }

    fun deleteOneFavoriteMovie(movieId: String) = viewModelScope.launch {
        repository.deleteOneFavoriteMovie(movieId)
    }
}

class FavoritesViewModelFactory(private val repository: MyListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}