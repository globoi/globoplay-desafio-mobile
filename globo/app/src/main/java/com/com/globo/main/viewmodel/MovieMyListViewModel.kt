package com.com.globo.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.com.globo.main.repository.MovieMyListLocalRepository
import com.com.globo.main.repository.MovieMyListRepository
import com.com.globo.repository.model.Movie

class MovieMyListViewModel(
    private val movieMyListRepository: MovieMyListRepository = MovieMyListLocalRepository()
) : ViewModel() {

    val answers = MutableLiveData<MovieMyListAnswers>()

    fun interact(interact: MovieMyListInteract) {
        when (interact) {
            is MovieMyListInteract.SearchMyListMovie -> searchList()
        }
    }

    private fun searchList() {
        movieMyListRepository.searchMoviesMyList(
            successListener = {
                if (it.isEmpty()) {
                    answers.postValue(MovieMyListAnswers.NotFoundMyListMovies)
                } else {
                    answers.postValue(
                        MovieMyListAnswers.MyListMovies(
                            it
                        )
                    )
                }
            },
            failureListener = { answers.postValue(MovieMyListAnswers.ErrorMyListMovie) }
        )
    }

}

sealed class MovieMyListInteract {
    object SearchMyListMovie : MovieMyListInteract()
}

sealed class MovieMyListAnswers {
    data class MyListMovies(val listMovies: List<Movie>) : MovieMyListAnswers()
    object NotFoundMyListMovies : MovieMyListAnswers()
    object ErrorMyListMovie : MovieMyListAnswers()
}