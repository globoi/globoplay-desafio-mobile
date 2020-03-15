package br.com.nerdrapido.themoviedbapp.data.model

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 03/03/2020
 *
 * Ref https://developers.themoviedb.org/3/lists/get-list-details
 */
data class ListGetDetailsObject(
    @SerializedName("created_by")
    val createdBy: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("favorite_count")
    val favoriteCount: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("items")
    val items: List<MovieListResultObject>,
    @SerializedName("item_count")
    val itemCount: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String?
)

