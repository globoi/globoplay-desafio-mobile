package com.gmribas.globoplaydesafiomobile.core.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIDS: List<Long>,
    override val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    override val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
): PosterItemInterface {
    override val poster: String? = posterPath
    override val backdrop: String? = backdropPath
}