package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.gmribas.globoplaydesafiomobile.core.domain.model.Video

data class VideosContainerDTO(
    val results: List<VideoDTO>
) {

    fun toDomain(): List<Video> = results.map { it.toDomain() }
}