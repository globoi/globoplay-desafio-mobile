package br.com.nerdrapido.themoviedbapp.data.repository.session

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface SessionRepository {

    fun getRequestToken(): String?

    fun setRequestToken(token: String?)

    fun getAccessToken(): String?

    fun setAccessToken(token: String?)

    fun getUserId(): String?

    fun setUserId(userId: String?)
}