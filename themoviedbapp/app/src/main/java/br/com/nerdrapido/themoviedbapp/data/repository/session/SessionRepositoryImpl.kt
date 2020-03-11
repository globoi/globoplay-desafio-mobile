package br.com.nerdrapido.themoviedbapp.data.repository.session

import android.app.Application

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SessionRepositoryImpl(private val application: Application): SessionRepository {

    companion object {

        private const val SESSION = "SESSION"

        private const val REQUEST_TOKEN = "REQUEST_TOKEN"

        private const val ACCESS_TOKEN = "ACCESS_TOKEN"

        private const val USER_ID = "ACCESS_TOKEN"

    }

    private val sharedPreferences = application.getSharedPreferences(SESSION, 0)

    override fun getRequestToken(): String? {
        return sharedPreferences.getString(REQUEST_TOKEN, null)
    }

    override fun setRequestToken(token: String?) {
        sharedPreferences.edit().putString(REQUEST_TOKEN, token).apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    override fun setAccessToken(token: String?) {
        sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply()
    }

    override fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID, null)
    }

    override fun setUserId(userId: String?) {
        sharedPreferences.edit().putString(USER_ID, userId).apply()
    }
}