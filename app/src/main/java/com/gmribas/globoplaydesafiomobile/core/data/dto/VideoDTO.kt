package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.gmribas.globoplaydesafiomobile.core.domain.model.Video
import com.google.gson.annotations.SerializedName

data class VideoDTO(
    @SerializedName("iso_639_1")
    val iso639_1: String,

    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String,
    val key: String,

    @SerializedName("published_at")
    val publishedAt: String,

    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val id: String
) {

    fun toDomain(): Video {
        return Video(iso639_1, iso3166_1, name, key, publishedAt, site, size, type, official, id)
    }

}