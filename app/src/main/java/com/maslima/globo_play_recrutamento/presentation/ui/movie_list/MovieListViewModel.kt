package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maslima.globo_play_recrutamento.domain.model.ImageConfig
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.repository.ConfigRepository
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())

    init {
        listMovies()
    }

    fun listMovies() {
        viewModelScope.launch {
            movies.value = repository.listMovies(1)
        }
    }
}