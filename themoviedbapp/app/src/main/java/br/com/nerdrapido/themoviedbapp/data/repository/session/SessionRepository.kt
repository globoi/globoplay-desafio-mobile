package br.com.nerdrapido.themoviedbapp.data.repository.session

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface SessionRepository {

    fun getRequestToken(): String?

    fun setRequestToken(value: String?)

    fun getSessionId(): String?

    fun setSessionId(value: String?)

    fun getIso6391(): String?

    fun setIso6391(value: String?)

    fun getIso31661(): String?

    fun setIso3161(value: String?)

    fun getName(): String?

    fun setName(value: String?)

    fun getIncludeAdult(): Boolean?

    fun setIncludeAdult(value: Boolean)

    fun getUserName(): String?

    fun setUserName(value: String?)

    fun getAccountId(): Int

    fun setAccountId(value: Int)
}