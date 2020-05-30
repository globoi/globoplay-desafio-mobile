package me.davidpcosta.tmdb.data.api

import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.data.model.*
import retrofit2.http.*
import rx.Observable

interface Api {

    // Authentication endpoints

    @GET("/3/authentication/token/new")
    fun createRequestToken(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<AuthenticationResult>

    @FormUrlEncoded
    @POST("/3/authentication/token/validate_with_login")
    fun validateWithLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("request_token") requestToken: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<AuthenticationResult>

    @FormUrlEncoded
    @POST("authentication/session/new")
    fun createSession(
        @Field("request_token") requestToken: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<SessionResult>

    // Watchlist

    @GET("account/9042674/watchlist/movies?session_id=c136a5251c169b1654a3ee2388cae932b2e8c0f2")
    fun watchlist(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<PagedResult<Movie>>

    // Movies

    @GET("genre/movie/list?session_id=c136a5251c169b1654a3ee2388cae932b2e8c0f2")
    fun genres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<Generes>

    @GET("discover/movie?session_id=c136a5251c169b1654a3ee2388cae932b2e8c0f2&page=1")
    fun moviesByGenre(
        @Query("with_genres") genreId: Long,
//        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("include_adult") includeAdults: Boolean = false,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<PagedResult<Movie>>

}