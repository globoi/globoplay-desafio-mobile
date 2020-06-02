package me.davidpcosta.tmdb.data.api

import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.data.model.*
import okhttp3.MediaType
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

    // Account Details

    @GET("account")
    fun accountDetails(
        @Query("session_id") sessionId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<AccountDetails>

    // Watchlist

    @GET("account/{account_id}/watchlist/movies")
    fun watchlist(
        @Path("account_id") accountId: Long,
        @Query("session_id") sessionId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<PagedResult<Movie>>

    @POST("account/{account_id}/watchlist")
    fun addToWatchlist(
        @Body media: Media,
        @Path("account_id") accountId: Long,
        @Query("session_id") sessionId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<WatchlistOperationResponse>

    @POST("account/{account_id}/watchlist")
    fun removeFromWatchlist(
        @Body media: Media,
        @Path("account_id") accountId: Long,
        @Query("session_id") sessionId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<WatchlistOperationResponse>

    // Movies

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<Generes>

    @GET("discover/movie")
    fun moviesByGenre(
        @Query("with_genres") genreId: Long,
        @Query("include_adult") includeAdults: Boolean = false,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<PagedResult<Movie>>

    @GET("movie/{movie_id}")
    fun movieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<MovieDetails>

    @GET("movie/{movie_id}/similar")
    fun similarMovies(
        @Path("movie_id") movieId: Long,
        @Query("include_adult") includeAdults: Boolean = false,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = BuildConfig.TMDB_LANGUAGE
    ): Observable<PagedResult<Movie>>

    @GET("movie/{movieId}/credits")
    fun movieCredits(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Observable<Credits>

}