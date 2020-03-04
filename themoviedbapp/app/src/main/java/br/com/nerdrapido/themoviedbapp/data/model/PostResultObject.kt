package br.com.nerdrapido.themoviedbapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 03/03/2020
 *
 * Referência https://developers.themoviedb.org/3/lists/remove-movie.
 */
data class PostResultObject(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)