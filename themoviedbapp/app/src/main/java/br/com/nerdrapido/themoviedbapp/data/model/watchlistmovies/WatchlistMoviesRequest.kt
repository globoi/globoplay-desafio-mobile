package br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/get-movie-watchlist
 */
data class WatchlistMoviesRequest(
    val accountId: String,
    val sessionId: String,
    val language: String?,
    val sortBy: String?,
    val page: Int?
)