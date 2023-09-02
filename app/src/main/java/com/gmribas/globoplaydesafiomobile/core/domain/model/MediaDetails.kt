package com.gmribas.globoplaydesafiomobile.core.domain.model

class MediaDetails(
    override val id: Int,
    override val title: String,
    override val isTvShow: Boolean,
    override val poster: String?,
    override val backdrop: String?,
    override val adult: Boolean,
    override val originalTitle: String,
    override val overview: String,
    override val spokenLanguages: List<Language>,
    override val videoList: List<Video>?
) : PosterItemInterface, DetailsInterface