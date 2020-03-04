package br.com.nerdrapido.themoviedbapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 03/03/2020
 */
data class TrendingObject(
    @SerializedName("page")
    val page : Int?,
    @SerializedName("results")
    val results : List<MovieListResultObject>?,
    @SerializedName("total_results")
    val totalResults : Int?,
    @SerializedName("total_pages")
    val totalPages : Int?
)