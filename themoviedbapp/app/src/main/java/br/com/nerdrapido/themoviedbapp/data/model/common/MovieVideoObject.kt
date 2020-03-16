package br.com.nerdrapido.themoviedbapp.data.model.common

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
data class MovieVideoObject(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("type")
    val type: String?
)