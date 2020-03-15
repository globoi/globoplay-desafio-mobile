package br.com.nerdrapido.themoviedbapp.data.repository.account

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
        @Path("account_id") sortBy: String?,
        @Query("language") region: String?,
        @Query("session_id") page: String?,
        @Query("sort_by") withGenres: String?,
        @Query("page") language: Int?
    ): FavoriteMoviesResponse

}