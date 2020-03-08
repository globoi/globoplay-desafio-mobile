package br.com.nerdrapido.themoviedbapp.domain.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
class ServiceInterceptor : Interceptor {

    /**
     * Movie Db API Token
     */
    val defaulToken: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZmI1ZmFjMjY1MzIyNzkyNzNkZGM1OGJkNGFmMjBlMCIsInN1YiI6IjVlNWYxOGQwNTVjOTI2MDAxOTU2MWQwMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5oMQzPLG2RW_0NaOyaDneiY4PjhFv1JHWWdtEQQdrn8"

    /**
     * Gets the token to be used in auth
     */
    private fun getToken(): String {
        /* TODO: chaveamento entre as chaves */
        return defaulToken
    }

    /**
     * Intercepts the retrofit request to make changes to the header
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        //Creates builder from request with defaul content-type
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
        //if auth is needed the bearer is added
        if (request.header("No-Authentication") == null) {
            val token = getToken()
            if (!token.isEmpty()) {
                val finalToken = "Bearer $token"
                requestBuilder
                    .addHeader("Authorization", finalToken)
            }

        }
        //finally returns the request tha has been built
        return chain.proceed(requestBuilder.build())
    }

}