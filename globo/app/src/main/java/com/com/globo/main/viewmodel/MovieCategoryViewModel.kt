package com.com.globo.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.com.globo.extension.onCleared
import com.com.globo.repository.MoviesCacheRepository
import com.com.globo.repository.model.MoviesResponse
import com.com.globo.repository.model.MoviesTitleCategory
import com.com.globo.main.usecase.MovieCategoryUseCase
import io.reactivex.disposables.Disposable
import java.util.*

class MovieCategoryViewModel(
    private val answersError: MutableLiveData<MovieCategoryErrorRequest>,
    private val moviesCacheRepository: MoviesCacheRepository,
    private val movieCategoryUseCase: MovieCategoryUseCase = MovieCategoryUseCase()
) : ViewModel() {

    private lateinit var currentMovie: MoviesTitleCategory
    private var disposable: Disposable? = null
    val answers by lazy { MutableLiveData<MovieCategoryAnswerSuccess>() }

    fun interact(interaction: MovieCategoryInteracts) {
        when (interaction) {
            is MovieCategoryInteracts.SearchMovieByCategory -> {
                disposable.onCleared()
                currentMovie = interaction.moviesTitleCategory

                if (moviesCacheRepository.contains(currentMovie)) {
                    answers.postValue(
                        MovieCategoryAnswerSuccess.AddMoviesToView(
                            moviesCacheRepository.getMovies(currentMovie)
                        )
                    )
                } else {
                    searchMovies(
                        currentMovie.name.toLowerCase(Locale.getDefault()),
                        interaction.page
                    )
                }
            }
        }
    }

    private fun searchMovies(category: String, page: Int) {
        disposable = movieCategoryUseCase.getMoviesByCategory(
            category = category,
            page = page,
            successListener = { whenMoviesArrived(it) },
            failureListener = { answersError.postValue(MovieCategoryErrorRequest.ShowMessageErrorNetwork) }
        )
    }

    private fun whenMoviesArrived(movies: MoviesResponse?) {
        //aqui, em um projeto real, devemos tratar a possibilidade de nao ter dados
        movies?.let { moviesResponse ->
            moviesResponse.listMovies?.let {
                moviesCacheRepository.addMovie(currentMovie, moviesResponse)
                answers.postValue(MovieCategoryAnswerSuccess.AddMoviesToView(moviesResponse))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.onCleared()
    }
}

sealed class MovieCategoryInteracts {
    data class SearchMovieByCategory(val moviesTitleCategory: MoviesTitleCategory, val page: Int) :
        MovieCategoryInteracts()
}

sealed class MovieCategoryAnswerSuccess {
    data class AddMoviesToView(val movies: MoviesResponse) : MovieCategoryAnswerSuccess()
}

sealed class MovieCategoryErrorRequest {
    object ShowMessageErrorNetwork : MovieCategoryErrorRequest()
}