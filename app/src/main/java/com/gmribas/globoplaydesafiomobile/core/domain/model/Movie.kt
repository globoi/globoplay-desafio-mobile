package com.gmribas.globoplaydesafiomobile.core.domain.model

import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media

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
    val voteCount: Long,
    override val isTvShow: Boolean = false
): PosterItemInterface, SimilarInterface {
    override val poster: String? = posterPath
    override val backdrop: String? = backdropPath

    fun toMedia(): Media {
        return Media(id, title, false, poster, backdrop)
    }
}