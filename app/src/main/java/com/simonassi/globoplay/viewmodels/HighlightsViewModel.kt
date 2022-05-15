package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class HighlightsViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    val movieSearchLiveData = MutableLiveData<Movie>()
    val tvSearchLiveData = MutableLiveData<Tv>()

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
}