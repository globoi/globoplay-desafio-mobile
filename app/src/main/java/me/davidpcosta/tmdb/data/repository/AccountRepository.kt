package me.davidpcosta.tmdb.data.repository

import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.AccountDetails
import me.davidpcosta.tmdb.data.model.AuthenticationResult
import me.davidpcosta.tmdb.data.model.SessionResult
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AccountRepository(private val api: Api) {

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

    fun accountDetails(sessionId: String): Observable<AccountDetails> {
        return api.accountDetails(sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
