package com.ftoniolo.globoplay.framework.network.response.film

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    val id: Long,
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