package br.com.nerdrapido.themoviedbapp.data.model.addfavorite

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/mark-as-favorite
 */
data class PostFavoriteRequest(
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("favorite")
    val favorite: Boolean
)