package com.com.ifood.repository

import com.com.ifood.repository.config.doRemoteRequest
import com.com.ifood.repository.config.provideMovieApi
import com.com.ifood.repository.model.MoviesResponse
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