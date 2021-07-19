package com.com.globo.repository

import com.com.globo.repository.model.MoviesResponse
import io.reactivex.disposables.Disposable

interface MovieRepository {

    fun getMoviesByCategory(
        category: String,
        page: Int,
        successListener: (model: MoviesResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable
}