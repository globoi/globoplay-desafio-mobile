package br.com.nerdrapido.themoviedbapp.domain.retrofit

import br.com.nerdrapido.themoviedbapp.BuildConfig
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
class ServiceInterceptor(private val sessionRepository: SessionRepository) : Interceptor {

    /**
     * Movie Db API Token
     */
    private val apiKey = BuildConfig.TMDB_API_KEY

    /**
     * Intercepts the retrofit request to make changes to the header
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}