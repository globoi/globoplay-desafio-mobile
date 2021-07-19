package com.com.globo.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.com.globo.details.model.MovieDetailsResponse
import com.com.globo.extension.onCleared
import com.com.globo.main.repository.MovieMyListLocalRepository
import com.com.globo.main.repository.MovieMyListRepository
import com.com.globo.repository.model.Movie
import io.reactivex.disposables.Disposable

class MovieDetailsViewModel(
    private val moviesDetailsUseCase: MovieDetailsUseCase = MovieDetailsUseCase(),
    private val movieListRepository: MovieMyListRepository = MovieMyListLocalRepository()
) :
    ViewModel() {

    val answersList by lazy { MutableLiveData<MovieMyListAnswers>() }
    val answers by lazy { MutableLiveData<MovieDetailsAnswer>() }
    private var disposable: Disposable? = null

    fun interact(interact: MovieDetailsInteract) {
        when (interact) {
            is MovieDetailsInteract.SearchMovie -> searchMovie(interact.movie)
            is MovieDetailsInteract.ScrollChanged -> whenScrollChanged(interact.percentageScroll)
            is MovieDetailsInteract.ChangeMovieOnMyList -> changeStateMovie(interact)
            is MovieDetailsInteract.ShouldChangeStateMyButton -> updateButtonMyList(interact.movie)
        }
    }

    private fun updateButtonMyList(movie: Movie) {
        movieListRepository
            .searchMoviesMyList(
                successListener = {
                    val isAddedNow = it.any { movie.id == it.id }
                    answersList.postValue(MovieMyListAnswers.ChangeStateMyListButton(isAddedNow))
                },
                failureListener = {}
            )
    }

    private fun changeStateMovie(interact: MovieDetailsInteract.ChangeMovieOnMyList) {
        if(interact.isAddMovieToList) {
            removeMovieInList(interact.movie)
        } else {
            insertMovieInList(interact.movie)
        }
    }

    private fun removeMovieInList(movie: Movie) {
        movieListRepository.deleteMovie(
            movie,
            successListener = { answersList.postValue(MovieMyListAnswers.ChangeStateMyList) },
            failureListener = { answersList.postValue(MovieMyListAnswers.ErrorOnChangeSelectedState) }
        )
    }

    private fun insertMovieInList(movie: Movie) {
        movieListRepository.insertMovie(
            movie,
            successListener = { answersList.postValue(MovieMyListAnswers.ChangeStateMyList) },
            failureListener = { answersList.postValue(MovieMyListAnswers.ErrorOnChangeSelectedState) }
        )
    }

    private fun whenScrollChanged(percentageScroll: Float) {
        val opacityPrincipalImage = Math.abs(percentageScroll - 1)
        val opacityBigPoster = opacityPrincipalImage - 0.3F
        val opacityShadowBigPoster = percentageScroll + 0.2F

        answers.postValue(
            MovieDetailsAnswer.AlphaChange(
                percentageScroll,
                opacityPrincipalImage,
                opacityBigPoster,
                opacityShadowBigPoster
            )
        )
    }

    private fun searchMovie(movie: Movie) {
        disposable = moviesDetailsUseCase.getMoviesDetails(
            movie.id,
            successListener = { it?.let { answers.postValue(MovieDetailsAnswer.ShowMovieDetails(it)) } }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.onCleared()
    }
}

sealed class MovieMyListAnswers {
    object ErrorOnChangeSelectedState : MovieMyListAnswers()
    object ChangeStateMyList : MovieMyListAnswers()
    data class ChangeStateMyListButton(val currentState: Boolean) : MovieMyListAnswers()
}

sealed class MovieDetailsInteract {
    data class SearchMovie(val movie: Movie) : MovieDetailsInteract()
    data class ScrollChanged(val percentageScroll: Float) : MovieDetailsInteract()
    data class ChangeMovieOnMyList(val movie: Movie, val isAddMovieToList: Boolean) : MovieDetailsInteract()
    data class ShouldChangeStateMyButton(val movie: Movie) : MovieDetailsInteract()
}

sealed class MovieDetailsAnswer {
    data class ShowMovieDetails(val movieDetails: MovieDetailsResponse) : MovieDetailsAnswer()
    data class AlphaChange(
        val percentageScroll: Float,
        val opacityPrincipalImage: Float,
        val opacityBigPoster: Float,
        val opacityShadowBigPoster: Float
    ) : MovieDetailsAnswer()

}