package me.davidpcosta.tmdb.data.repository

import android.mtp.MtpObjectInfo
import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.dao.AppDatabase
import me.davidpcosta.tmdb.data.dao.MovieDao
import me.davidpcosta.tmdb.data.model.Media
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.PagedResult
import me.davidpcosta.tmdb.data.model.WatchlistOperationResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class WatchlistRepository(private val api: Api, private val movieDao: MovieDao) {


    fun watchlist(accountId: Long, sessionId: String): Observable<PagedResult<Movie>> {
        return api.watchlist(accountId, sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addToWatchlist(accountId: Long, sessionId: String, movie: Movie): Observable<WatchlistOperationResponse> {
        val m = Media(mediaType = "movie", mediaId = movie.id, watchlist = true)
        return api.addToWatchlist(accountId = accountId, sessionId = sessionId, media = m)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun removeFromWatchlist(accountId: Long, sessionId: String, movieId: Long): Observable<WatchlistOperationResponse> {
        val m = Media("movie", movieId, false)
        return api.removeFromWatchlist(accountId = accountId, sessionId = sessionId, media = m)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
