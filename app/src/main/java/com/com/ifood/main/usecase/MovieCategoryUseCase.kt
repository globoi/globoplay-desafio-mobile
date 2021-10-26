package com.com.ifood.main.usecase

import com.com.ifood.repository.MovieRemoteRepository
import com.com.ifood.repository.MovieRepository
import com.com.ifood.repository.model.MoviesResponse
import io.reactivex.disposables.Disposable

class MovieCategoryUseCase(private val repository: MovieRepository = MovieRemoteRepository()) {

    fun getMoviesByCategory(
        category: String,
        page: Int,
        successListener: (model: MoviesResponse?) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return repository.getMoviesByCategory(category, page, successListener, failureListener)
    }
}