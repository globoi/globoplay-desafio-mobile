package com.com.ifood.main.repository

import com.com.ifood.repository.model.Movie
import com.com.ifood.repository.room.MovieDao
import com.com.ifood.repository.room.provideMovieLocalRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MovieMyListLocalRepository : MovieMyListRepository {

    override fun searchMoviesMyList(
        successListener: (listMovies: List<Movie>) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return provideMovieLocalRepository()
            .map { it.getMovies() }
            .subscribe(successListener, failureListener)
    }

    override fun insertMovie(
        movie: Movie,
        successListener: () -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return subscribe(
            provideMovieLocalRepository().doOnNext { it.insert(movie) },
            successListener, failureListener
        )
    }

    private inline fun subscribe(
        observable: Observable<MovieDao>,
        crossinline successListener: () -> Unit,
        crossinline failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ successListener() }, { failureListener(it) })
    }

    override fun deleteMovie(
        movie: Movie,
        successListener: () -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable {
        return subscribe(
            provideMovieLocalRepository().doOnNext { it.deleteById(movie.id) },
            successListener, failureListener
        )
    }
}

interface MovieMyListRepository {

    fun searchMoviesMyList(
        successListener: (listMovies: List<Movie>) -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable

    fun insertMovie(
        movie: Movie,
        successListener: () -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable

    fun deleteMovie(
        movie: Movie,
        successListener: () -> Unit,
        failureListener: (exception: Throwable) -> Unit
    ): Disposable
}