package com.nroncari.movieplay.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailDomain(
    val id: Int? = 0,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    var backdropPath: String
)