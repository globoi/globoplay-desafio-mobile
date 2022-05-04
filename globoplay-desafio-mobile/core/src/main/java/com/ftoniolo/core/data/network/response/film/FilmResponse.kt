package com.ftoniolo.core.data.network.response.film

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    val id: Long,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("genre_ids")
    val genreIds: List<Long>,
    @SerializedName("poster_path")
    val posterPath: String,
)