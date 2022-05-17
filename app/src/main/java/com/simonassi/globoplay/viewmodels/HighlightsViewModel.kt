package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simonassi.globoplay.data.*
import com.simonassi.globoplay.data.favorite.FavoriteRepository
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HighlightsViewModel @Inject constructor(
    private val repository: TMDBRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    val movieSearchLiveData = MutableLiveData<Movie>()
    val tvSearchLiveData = MutableLiveData<Tv>()
    val favoriteSearchLiveData = MutableLiveData<Favorite?>()
    val relatedMovies = MutableLiveData<List<Movie>>()

    fun getMovieById(id: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val movie = withContext(Dispatchers.Default) {
                repository.getMovieById(id)
            }
            movieSearchLiveData.value = movie
        }
    }

    fun getTvById(id: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val tv = withContext(Dispatchers.Default) {
                repository.getTvById(id)
            }
            tvSearchLiveData.value = tv
        }
    }

    fun saveFavorite(favorite: Favorite) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                favoriteRepository.saveFavorite(favorite)
            }
        }
    }

    fun getFavoriteById(id: Long){
        CoroutineScope(Dispatchers.Main).launch {
            val favorite = withContext(Dispatchers.Default) {
                favoriteRepository.getFavoriteById(id)
            }
            if(favorite.isNotEmpty()){
                favoriteSearchLiveData.value = favorite[0]
            }else{
                favoriteSearchLiveData.value = null
            }
        }
    }

    fun deleteFavoriteById(id: Long){
        CoroutineScope(Dispatchers.Main).launch {
            favoriteRepository.deleteById(id)
        }
    }

    fun getRelatedMovies(genreId: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.getRelatedMovies(genreId)
            }
            relatedMovies.value = movies
        }
    }

}