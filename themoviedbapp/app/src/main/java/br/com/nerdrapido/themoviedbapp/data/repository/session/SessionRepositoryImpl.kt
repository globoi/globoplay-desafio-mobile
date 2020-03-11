package br.com.nerdrapido.themoviedbapp.data.repository.session

import android.app.Application

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SessionRepositoryImpl(private val application: Application): SessionRepository {

    private val sharedPrefKey = "SESSION"

    private val requestTokenKey = "REQUEST_TOKEN"

    private val accessTokenKey = "ACCESS_TOKEN"

    private val sharedPreferences = application.getSharedPreferences(sharedPrefKey, 0)

    override fun getRequestToken(): String? {
        return sharedPreferences.getString(requestTokenKey, null)
    }

    override fun setRequestToken(token: String?) {
        sharedPreferences.edit().putString(requestTokenKey, token).apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(accessTokenKey, null)
    }

    override fun setAccessToken(token: String?) {
        sharedPreferences.edit().putString(accessTokenKey, token).apply()
    }
}