package com.ftoniolo.core.data.network.response.details

import com.ftoniolo.core.data.network.response.genre.GenreResponse
import com.google.gson.annotations.SerializedName

data class DetailsDataWrapperResponse(
    val id: Long,
    val genres: List<GenreResponse>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("production_countries")
    val productionCountries: List<DetailsProdCountriesResponse>,
    )