package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simonassi.globoplay.data.TMDBRepository
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.utilities.Generator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoreContentViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    val moreContentLiveData = MutableLiveData<List<Movie>>(Generator.generateInitialMovies())

    fun getTrendingMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.getTrendingMovies()
            }
            moreContentLiveData.value = movies
        }
    }
}