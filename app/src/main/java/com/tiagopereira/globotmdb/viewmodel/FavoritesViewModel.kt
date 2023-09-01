package com.tiagopereira.globotmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tiagopereira.globotmdb.database.entities.Movie
import com.tiagopereira.globotmdb.viewmodel.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel constructor(private val favoritesRepository: FavoritesRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    private val _movies = currentQuery.switchMap { _ ->
        favoritesRepository.getFavoriteMovies().cachedIn(viewModelScope)
    }
    val movies: LiveData<PagingData<Movie>> = _movies

    private val _showMessage = MutableLiveData<Boolean>()
    val showMessage: LiveData<Boolean> = _showMessage

    fun countFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            if(favoritesRepository.getCountFavorites() > 0){
                _showMessage.postValue(false)
            } else {
                _showMessage.postValue(true)
            }
        }
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}