package br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 *
 * https://developers.themoviedb.org/3/movies/get-movie-account-states
 */
data class MovieAccountStateResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("favorite")
    val favorite: Boolean?,
    @SerializedName("watchlist")
    val watchlist: Boolean?
)