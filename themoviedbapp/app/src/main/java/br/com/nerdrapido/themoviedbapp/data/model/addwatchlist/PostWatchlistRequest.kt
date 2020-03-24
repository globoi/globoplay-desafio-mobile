package br.com.nerdrapido.themoviedbapp.data.model.addwatchlist

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/add-to-watchlist
 */
data class PostWatchlistRequest(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("watchlist")
    val watchlist: Boolean
)