package com.ftoniolo.globoplay.framework.network.response.film

import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.BuildConfig
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("genre_ids")
    val genreIds: List<Long>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("original_language")
    val originalLanguage: String
)

fun FilmResponse.toFilmModel(): Film {
    return Film(
        id = this.id,
        overview = this.overview,
        title = this.title,
        genreIds = this.genreIds,
        imageUrl = "${BuildConfig.THUMBNAIL_URL}${this.posterPath}",
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage
    )
}