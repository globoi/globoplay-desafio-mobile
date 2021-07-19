package com.com.globo.repository

import com.com.globo.repository.config.doRemoteRequest
import com.com.globo.repository.config.provideMovieApi
import com.com.globo.repository.model.MoviesResponse
import io.reactivex.disposables.Disposable

class MovieRemoteRepository : MovieRepository {

    override fun getMoviesByCategory(
        category: String,
        page: Int,
        successListener: (model: MoviesResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return doRemoteRequest(
            {
                provideMovieApi()
                    .getMovies(category = category, page = page)
            },
            successListener, failureListener
        )
    }
}