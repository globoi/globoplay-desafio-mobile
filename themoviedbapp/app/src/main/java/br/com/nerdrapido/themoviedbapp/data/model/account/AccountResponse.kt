package br.com.nerdrapido.themoviedbapp.data.model.account

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
data class AccountResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("include_adult")
    val includeAdult: Boolean?,
    @SerializedName("username")
    val username: String?
)