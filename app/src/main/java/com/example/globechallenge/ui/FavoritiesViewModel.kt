package com.example.globechallenge.ui

import androidx.lifecycle.*
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import com.example.globechallenge.data.repository.Favorities.FavoriteMoviesRepository
import kotlinx.coroutines.launch

class FavoritiesViewModel(private val repository: FavoriteMoviesRepository) : ViewModel() {

    val allFavoriteMovies: LiveData<List<FavoriteMoviesEntity>> =
        repository.allFavoriteMovies.asLiveData()

    fun insert(favoriteMoviesEntity: FavoriteMoviesEntity) = viewModelScope.launch {
        repository.insert(favoriteMoviesEntity)
    }

    fun deleteOneFavoriteMovie() = viewModelScope.launch {
        repository.deleteOneFavoriteMovie()
    }
}

class FavoritiesViewModelFactory(private val repository: FavoriteMoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritiesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}