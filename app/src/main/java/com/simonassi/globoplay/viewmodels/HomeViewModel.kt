package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.TMDBRepository
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.utilities.Generator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    val moviesLiveData = MutableLiveData<List<Movie>>(Generator.generateInitialMovies())
    val tvsLiveData = MutableLiveData<List<Tv>>(Generator.generateInitialTv())

    fun getMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.getMovies()
            }
            moviesLiveData.value = movies
        }
    }

    fun getTvs() {
        CoroutineScope(Dispatchers.Main).launch {
            val tvs = withContext(Dispatchers.Default) {
                repository.getTvs()
            }
            tvsLiveData.value = tvs
        }
    }
}