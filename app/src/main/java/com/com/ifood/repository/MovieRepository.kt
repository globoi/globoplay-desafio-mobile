package com.com.ifood.repository

import com.com.ifood.repository.model.MoviesResponse
import io.reactivex.disposables.Disposable

interface MovieRepository {

    fun getMoviesByCategory(
        category: String,
        page: Int,
        successListener: (model: MoviesResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable
}