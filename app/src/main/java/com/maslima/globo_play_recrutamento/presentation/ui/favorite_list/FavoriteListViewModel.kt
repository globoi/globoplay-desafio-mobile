package com.maslima.globo_play_recrutamento.presentation.ui.favorite_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieListEvent
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import kotlinx.coroutines.launch

const val TAG = "FavoriteList"

class FavoriteListViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val loading = mutableStateOf(false)

    init {
        onTriggerEvent(MovieListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event: MovieListEvent) {
        viewModelScope.launch {
            try {
                listMovies()
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
            }
        }
    }

    private suspend fun listMovies() {
        loading.value = true
        val result = repository.listFavorites()
        this.movies.value = result
        loading.value = false
    }
}