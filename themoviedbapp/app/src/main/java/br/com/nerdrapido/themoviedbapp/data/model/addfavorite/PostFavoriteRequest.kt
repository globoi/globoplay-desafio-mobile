package br.com.nerdrapido.themoviedbapp.data.model.addfavorite

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * https://developers.themoviedb.org/3/account/mark-as-favorite
 */
data class PostFavoriteRequest(
    val sessionId: String,
    val mediaType: String,
    val mediaId: Int,
    val favorite: Boolean
)