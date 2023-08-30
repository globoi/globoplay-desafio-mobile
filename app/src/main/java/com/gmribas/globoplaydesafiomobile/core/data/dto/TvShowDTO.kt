package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowDTO(
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("genre_ids")
    val genreIDS: List<Long>,

    val id: Int,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
) {
    fun toDomain(): TvShow {
        return TvShow(
            backdropPath,
            firstAirDate,
            genreIDS,
            id,
            name,
            originCountry,
            originalLanguage,
            originalName,
            overview,
            popularity,
            posterPath,
            voteAverage,
            voteCount
        )
    }
}