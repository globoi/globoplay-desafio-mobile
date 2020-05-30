package me.davidpcosta.tmdb.data.repository

import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.AuthenticationResult
import me.davidpcosta.tmdb.data.model.SessionResult
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AuthenticationRepository(private val api: Api) {

    fun login(username: String, password: String, requestToken: String): Observable<AuthenticationResult> {
        return api.validateWithLogin(username, password, requestToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createRequestToken(): Observable<AuthenticationResult> {
        return api.createRequestToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createSession(requestToken: String): Observable<SessionResult> {
        return api.createSession(requestToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout() {
        TODO("Handle logout")
    }

}
