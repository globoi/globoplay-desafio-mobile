package br.com.nerdrapido.themoviedbapp.data.repository.account

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteRequest
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteResponse
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistRequest
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistResponse
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesResponse
import retrofit2.http.*

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
interface AccountService {

    /**
     * https://developers.themoviedb.org/3/account/get-account-details
     */
    @GET("account")
    suspend fun getAccount(
        @Query("session_id") page: String?
    ): AccountResponse

    /**
     * https://developers.themoviedb.org/3/account/get-favorite-movies
     */
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: String?,
        @Query("language") language: String?,
        @Query("session_id") sessionId: String?,
        @Query("sort_by") sortBy: String?,
        @Query("page") page: Int?
    ): FavoriteMoviesResponse

    /**
     * https://developers.themoviedb.org/3/account/get-movie-watchlist
     */
    @GET("account/{account_id}/watchlist/movies")
    suspend fun getWatchlistMovies(
        @Path("account_id") accountId: String?,
        @Query("language") language: String?,
        @Query("session_id") sessionId: String?,
        @Query("sort_by") sortBy: String?,
        @Query("page") page: Int?
    ): WatchlistMoviesResponse

    /**
     * https://developers.themoviedb.org/3/account/mark-as-favorite
     */
    @POST("account/{account_id}/favorite")
    suspend fun markMovieToFavorite(
        @Path("account_id") accountId: String?,
        @Query("session_id") sessionId: String?,
        @Body body: PostFavoriteRequest
    ): PostFavoriteResponse

    /**
     * https://developers.themoviedb.org/3/account/add-to-watchlist
     */
    @POST("account/{account_id}/watchlist")
    suspend fun saveMovieToWatchlist(
        @Path("account_id") accountId: String?,
        @Query("session_id") sessionId: String?,
        @Body body: PostWatchlistRequest
    ): PostWatchlistResponse
}