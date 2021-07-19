package com.example.globechallenge.ui

import androidx.lifecycle.*
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import com.example.globechallenge.data.repository.favorites.FavoriteMoviesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: FavoriteMoviesRepository) : ViewModel() {

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

class FavoritiesViewModelFactory(private val repository: FavoriteMoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}