package br.com.nerdrapido.themoviedbapp.data.model.movie

import br.com.nerdrapido.themoviedbapp.data.model.common.ProductionCountries
import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
data class MovieResponse(
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = ArrayList(),
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries>?
)