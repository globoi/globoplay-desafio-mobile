package br.com.nerdrapido.themoviedbapp.data.model.favoritemovies

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/get-favorite-movies
 */
data class FavoriteMoviesRequest(
    val accountId: String,
    val sessionId: String,
    val language: String?,
    val sortBy: String?,
    val page: Int?
)