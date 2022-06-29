package com.nroncari.movieplay.domain.model

data class MovieListItemDomain(
    val id: Long,
    val originalTitle: String,
    val title: String,
    val posterPath: String
)