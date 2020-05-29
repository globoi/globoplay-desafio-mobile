package me.davidpcosta.tmdb.data

import me.davidpcosta.tmdb.data.model.Generes
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesRepository(private val api:Api) {

    fun generes(): Observable<Generes> {
        return api.generes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
