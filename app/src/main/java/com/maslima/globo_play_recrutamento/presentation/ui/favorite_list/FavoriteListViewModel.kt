package com.maslima.globo_play_recrutamento.presentation.ui.favorite_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.viewinterop.viewModel
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieListEvent
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import kotlinx.coroutines.*

const val TAG = "FavoriteList"

class FavoriteListViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var movies: List<Movie> = listOf()
    val loading = mutableStateOf(false)

    init {
        onTriggerEvent(MovieListEvent.NewSearchEvent, {})
    }

    fun onTriggerEvent(event: MovieListEvent,  success: (List<Movie>) -> Unit,) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val listFavorites = repository.listFavorites()
                success(listFavorites)
            }
        } catch (e: Exception) {
            Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
        }
    }
}