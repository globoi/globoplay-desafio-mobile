package me.davidpcosta.tmdb.data

import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.Result
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class WatchlistRepository(private val api:Api) {

    fun watchlist(): Observable<Result<Movie>> {
        return api.watchlist()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
