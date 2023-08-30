package com.gmribas.globoplaydesafiomobile.core.domain.model

data class TvShow (
    val backdropPath: String?,
    val firstAirDate: String,
    val genreIDS: List<Long>,
    override val id: Int,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Long
): PosterItemInterface, SimilarInterface {
    override val title: String = originalName
    override val poster: String? = posterPath
    override val backdrop: String? = backdropPath
}