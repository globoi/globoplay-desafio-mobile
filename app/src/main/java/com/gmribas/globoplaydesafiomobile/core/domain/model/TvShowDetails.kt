package com.gmribas.globoplaydesafiomobile.core.domain.model

import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaDetailsEntity

data class TvShowDetails(
    override val adult: Boolean,
    val backdropPath: String?,
    val episodeRunTime: List<Any?>,
    val firstAirDate: String,
    val homepage: String,
    override val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Long,
    val numberOfSeasons: Long,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    override val overview: String,
    val popularity: Double,
    val posterPath: String,
    override val spokenLanguages: List<Language>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Long,
    override val isTvShow: Boolean = true
) : DetailsInterface, PosterItemInterface {
    override val originalTitle: String = originalName
    override val title: String = name
    override val poster: String = posterPath
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