package me.davidpcosta.tmdb.data.repository

import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesRepository(private val api: Api) {

    fun genres(): Observable<Generes> {
        return api.genres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun moviesByGenre(genreId: Long): Observable<PagedResult<Movie>> {
        return api.moviesByGenre(genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun similarMovies(movieId: Long): Observable<PagedResult<Movie>> {
        return api.similarMovies(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun credits(movieId: Long): Observable<Credits> {
        return api.movieCredits(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun movieDetails(movieId: Long): Observable<MovieDetails> {
        return api.movieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
