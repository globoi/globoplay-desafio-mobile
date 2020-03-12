package br.com.nerdrapido.themoviedbapp.data.repository.session

import android.app.Application

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SessionRepositoryImpl(private val application: Application): SessionRepository {

    companion object {

        private const val SESSION = "SESSION"

        private const val REQUEST_TOKEN = "REQUEST_TOKEN"

        private const val SESSION_ID = "SESSION_ID"

    }

    private val sharedPreferences = application.getSharedPreferences(SESSION, 0)

    override fun getRequestToken(): String? {
        return sharedPreferences.getString(REQUEST_TOKEN, null)
    }

    override fun setRequestToken(token: String?) {
        sharedPreferences.edit().putString(REQUEST_TOKEN, token).apply()
    }

    override fun getSessionId(): String? {
        return sharedPreferences.getString(SESSION_ID, null)
    }

    override fun setSessionID(sessionID: String?) {
        sharedPreferences.edit().putString(SESSION_ID, sessionID).apply()
    }


}