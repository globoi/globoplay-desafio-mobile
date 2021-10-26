package com.com.ifood.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.com.ifood.extension.onCleared
import com.com.ifood.main.usecase.MovieCategoryUseCase
import com.com.ifood.repository.MoviesCacheRepository
import com.com.ifood.repository.model.MoviesResponse
import com.com.ifood.repository.model.MoviesTitleCategory
import io.reactivex.disposables.Disposable
import java.util.*

class MovieCategoryViewModel(
    private val answersError: MutableLiveData<MovieCategoryErrorRequest>,
    private val moviesCacheRepository: MoviesCacheRepository,
    private val movieCategoryUseCase: MovieCategoryUseCase = MovieCategoryUseCase()
) : ViewModel() {

    private lateinit var currentMovie: MoviesTitleCategory
    private lateinit var movieResponse: MoviesResponse

    private var disposable: Disposable? = null

    @Volatile
    private var isLoadingMovie = false

    val answers by lazy { MutableLiveData<MovieCategoryAnswerSuccess>() }

    fun interact(interaction: MovieCategoryInteracts) {
        when (interaction) {
            is MovieCategoryInteracts.SearchMoreMovie -> {
                loadMorePage()
            }
            is MovieCategoryInteracts.SearchMovieByCategory -> {
                disposable.onCleared()
                currentMovie = interaction.moviesTitleCategory

                if (moviesCacheRepository.contains(currentMovie)) {
                    this.movieResponse = moviesCacheRepository.getMovies(currentMovie)
                    answers.postValue(
                        MovieCategoryAnswerSuccess.AddMoviesToView(
                            moviesCacheRepository.getMovies(currentMovie)
                        )
                    )
                } else {
                    searchMovies(page = 1)
                }
            }
        }
    }

    @Synchronized
    private fun loadMorePage() {
        if (movieResponse.totalPages > movieResponse.nextPageToLoad &&
                !isLoadingMovie) {
            searchMovies()
        }
    }

    private fun searchMovies(
        category: String = currentMovie.name.toLowerCase(Locale.getDefault()),
        page: Int = movieResponse.nextPageToLoad
    ) {
        isLoadingMovie = true
        disposable = movieCategoryUseCase.getMoviesByCategory(
            category = category,
            page = page,
            successListener = { whenMoviesArrived(it, page == 1) },
            failureListener = {
                isLoadingMovie = false
                answersError.postValue(MovieCategoryErrorRequest.ShowMessageErrorNetwork)
            }
        )
    }

    private fun whenMoviesArrived(movies: MoviesResponse?, isFirstPage: Boolean) {
        movies?.let { moviesResponse ->
            moviesResponse.listMovies?.let {

                if (isFirstPage) {
                    this.movieResponse = moviesResponse
                    this.movieResponse.nextPageToLoad = 2
                    moviesCacheRepository.addMovie(currentMovie, this.movieResponse)
                } else {
                    movieResponse.listMovies!!.addAll(movies.listMovies!!)
                    movieResponse.nextPageToLoad = movieResponse.nextPageToLoad + 1
                }
                answers.postValue(MovieCategoryAnswerSuccess.AddMoviesToView(moviesResponse))
                isLoadingMovie = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.onCleared()
    }
}

sealed class MovieCategoryInteracts {
    object SearchMoreMovie: MovieCategoryInteracts()
    data class SearchMovieByCategory(val moviesTitleCategory: MoviesTitleCategory) :
        MovieCategoryInteracts()
}

sealed class MovieCategoryAnswerSuccess {
    data class AddMoviesToView(val movies: MoviesResponse) : MovieCategoryAnswerSuccess()
}

sealed class MovieCategoryErrorRequest {
    object ShowMessageErrorNetwork : MovieCategoryErrorRequest()
}