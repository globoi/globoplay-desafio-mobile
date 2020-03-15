package br.com.nerdrapido.themoviedbapp.data.repository.session

import android.app.Application

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SessionRepositoryImpl(private val application: Application) : SessionRepository {

    companion object {

        private const val SESSION = "SESSION"

        private const val REQUEST_TOKEN = "REQUEST_TOKEN"

        private const val ISO_639_1 = "ISO_639_1"

        private const val ISO_3166_1 = "ISO_3166_1"

        private const val ACCOUNT_ID = "ACCOUNT_ID"

        private const val NAME = "NAME"

        private const val INCLUDE_ADULT = "INCLUDE_ADULT"

        private const val USERNAME = "USERNAME"

        private const val SESSION_ID = "SESSION_ID"

    }

    private val sharedPreferences = application.getSharedPreferences(SESSION, 0)

    override fun getRequestToken(): String? {
        return sharedPreferences.getString(REQUEST_TOKEN, null)
    }

    override fun setRequestToken(value: String?) {
        sharedPreferences.edit().putString(REQUEST_TOKEN, value).apply()
    }

    override fun getSessionId(): String? {
        return sharedPreferences.getString(SESSION_ID, null)
    }

    override fun setSessionId(value: String?) {
        sharedPreferences.edit().putString(SESSION_ID, value).apply()
    }

    override fun getIso6391(): String? {
        return sharedPreferences.getString(ISO_639_1, null)
    }

    override fun setIso6391(value: String?) {
        sharedPreferences.edit().putString(ISO_639_1, value).apply()
    }

    override fun getIso31661(): String? {
        return sharedPreferences.getString(ISO_3166_1, null)
    }

    override fun setIso3161(value: String?) {
        sharedPreferences.edit().putString(ISO_3166_1, value).apply()
    }

    override fun getName(): String? {
        return sharedPreferences.getString(NAME, null)
    }

    override fun setName(value: String?) {
        sharedPreferences.edit().putString(NAME, value).apply()
    }

    override fun getIncludeAdult(): Boolean? {
        return sharedPreferences.getBoolean(INCLUDE_ADULT, false)
    }

    override fun setIncludeAdult(value: Boolean) {
        sharedPreferences.edit().putBoolean(INCLUDE_ADULT, value).apply()
    }

    override fun getUserName(): String? {
        return sharedPreferences.getString(USERNAME, null)
    }

    override fun setUserName(value: String?) {
        sharedPreferences.edit().putString(USERNAME, value).apply()
    }

    override fun getAccountId(): Int {
        return sharedPreferences.getInt(ACCOUNT_ID, 0)
    }

    override fun setAccountId(value: Int) {
        sharedPreferences.edit().putInt(ACCOUNT_ID, value).apply()
    }


}