package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simonassi.globoplay.data.TMDBRepository
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
class SearchViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    val resultSearchLiveData = MutableLiveData<List<Movie>>()

    fun getMovieByKeyword(key: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.searchMoviesByKeyword(key)
            }
            resultSearchLiveData.value = movies
        }
    }

}