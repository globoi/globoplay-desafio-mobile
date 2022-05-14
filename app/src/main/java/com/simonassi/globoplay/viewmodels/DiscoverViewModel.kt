package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import com.simonassi.globoplay.api.TMDBService
import com.simonassi.globoplay.data.TMDBMovie
import com.simonassi.globoplay.data.TMDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    val moviesLiveData = MutableLiveData<List<TMDBMovie>>()

    fun getMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.getMovies()
            }
            moviesLiveData.value = movies
        }
    }

    class DiscoverViewModelFactory(private val repository: TMDBRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DiscoverViewModel(repository) as T
        }
    }
}