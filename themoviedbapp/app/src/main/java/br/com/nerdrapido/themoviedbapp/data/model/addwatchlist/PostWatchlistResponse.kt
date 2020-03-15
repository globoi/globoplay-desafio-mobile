package br.com.nerdrapido.themoviedbapp.data.model.addwatchlist

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/add-to-watchlist
 */
data class PostWatchlistResponse(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?
)