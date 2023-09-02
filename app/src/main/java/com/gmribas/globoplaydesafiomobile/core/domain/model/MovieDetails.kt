package com.gmribas.globoplaydesafiomobile.core.domain.model

import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaDetailsEntity

data class MovieDetails(
    override val adult: Boolean,
    val backdropPath: String,
    val homepage: String,
    override val id: Int,
    val imdbID: String?,
    val originalLanguage: String,
    override val originalTitle: String,
    override val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val status: String,
    override val title: String,
    val video: Boolean,
    override val spokenLanguages: List<Language>,
    override val isTvShow: Boolean = false,
    override val videoList: List<Video>?
) : DetailsInterface, PosterItemInterface {
    override val poster: String? = posterPath
    override val backdrop: String? = backdropPath

    fun toMediaEntity(): MediaDetailsEntity {
        return MediaDetailsEntity(
            id = id,
            title = title,
            isTvShow = isTvShow,
            poster = poster,
            backdrop = backdrop,
            adult = adult,
            originalTitle = originalTitle,
            overview = overview
        )
    }
}