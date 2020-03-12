package br.com.nerdrapido.themoviedbapp.data.model.discover

import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 03/03/2020
 *
 * Ref https://developers.themoviedb.org/3/discover/movie-discover
 */
data class DiscoverResponse(
    @SerializedName("page")
    val page : Int?,
    @SerializedName("results")
    val results : List<MovieListResultObject>?,
    @SerializedName("total_results")
    val totalResults : Int?,
    @SerializedName("total_pages")
    val totalPages : Int?
)