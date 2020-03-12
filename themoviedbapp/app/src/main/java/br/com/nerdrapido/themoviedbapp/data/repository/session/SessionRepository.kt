package br.com.nerdrapido.themoviedbapp.data.repository.session

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface SessionRepository {

    fun getRequestToken(): String?

    fun setRequestToken(token: String?)

    fun getSessionId(): String?

    fun setSessionID(sessionID: String?)
}