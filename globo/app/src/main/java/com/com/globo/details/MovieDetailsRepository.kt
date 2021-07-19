package com.com.globo.details

import com.com.globo.repository.config.doRemoteRequest
import com.com.globo.repository.config.provideMovieApi
import com.com.globo.details.model.MovieDetailsResponse
import io.reactivex.disposables.Disposable


class MovieDetailsUseCase(private val repository: MovieDetailsRemoteRepository = MovieDetailsRemoteRepository()) {

    fun getMoviesDetails(
        movieId: Long,
        successListener: (model: MovieDetailsResponse?) -> Unit
    ): Disposable {
        return repository.getMoviesDetails(movieId, successListener, {})
    }
}

class MovieDetailsRemoteRepository : MovieDetailsRepository {

    override fun getMoviesDetails(
        movieId: Long,
        successListener: (model: MovieDetailsResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return doRemoteRequest(
            { provideMovieApi().getMoviesDetails(movieId) },
            successListener, failureListener
        )
    }
}

interface MovieDetailsRepository {

    fun getMoviesDetails(
        movieId: Long,
        successListener: (model: MovieDetailsResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable
}