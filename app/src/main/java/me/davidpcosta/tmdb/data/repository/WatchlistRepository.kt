package me.davidpcosta.tmdb.data.repository

import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.PagedResult
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class WatchlistRepository(private val api: Api) {

    fun watchlist(): Observable<PagedResult<Movie>> {
        return api.watchlist()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
