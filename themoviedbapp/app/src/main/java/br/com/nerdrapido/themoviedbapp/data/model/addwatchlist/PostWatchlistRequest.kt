package br.com.nerdrapido.themoviedbapp.data.model.addwatchlist

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/add-to-watchlist
 */
data class PostWatchlistRequest(
    val sessionId: String,
    val mediaType: String,
    val mediaId: Int,
    val watchlist: Boolean
)