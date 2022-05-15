package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import com.simonassi.globoplay.data.Movie
import com.simonassi.globoplay.data.TMDBRepository
import com.simonassi.globoplay.data.Tv
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

    val moviesLiveData = MutableLiveData<List<Movie>>()
    val tvsLiveData = MutableLiveData<List<Tv>>()

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

    fun getMovieById(id: Int): Movie? {
        return moviesLiveData.value?.filter { it.id == id }?.get(0)
    }

    fun getTvById(id: Int): Tv? {
        return tvsLiveData.value?.filter { it.id == id }?.get(0)
    }

    class DiscoverViewModelFactory(private val repository: TMDBRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}