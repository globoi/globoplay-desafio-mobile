package br.com.nerdrapido.themoviedbapp.data.model.recommendation

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 *
 * https://developers.themoviedb.org/3/movies/get-movie-recommendations
 */
data class RecommendationResponse(
    @SerializedName("page")
    val page : Int?,
    @SerializedName("results")
    val results : List<MovieListResultObject>?,
    @SerializedName("total_results")
    val totalResults : Int?,
    @SerializedName("total_pages")
    val totalPages : Int?
)