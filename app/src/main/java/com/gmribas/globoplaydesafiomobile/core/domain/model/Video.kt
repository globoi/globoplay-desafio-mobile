package com.gmribas.globoplaydesafiomobile.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val iso639_1: String,
    val iso3166_1: String,
    val name: String,
    val key: String,
    val publishedAt: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val id: String
): Parcelable