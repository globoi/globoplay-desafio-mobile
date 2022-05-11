package com.ftoniolo.globoplay.presentation.home

import androidx.annotation.StringRes
import com.ftoniolo.core.domain.model.Film

data class HomeChildVE(
    val id: Long,
    val overview: String,
    val title: String,
    val genreIds: List<Long>,
    val imageUrl: String,
    val releaseDate: String,
    val originalLanguage: String
)

fun HomeChildVE.toFilm(): Film {
    return Film(
        id = this.id,
        title = this.title,
        overview = this.overview,
        genreIds = this.genreIds,
        imageUrl = this.imageUrl,
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage,
    )
}

data class HomeParentVE(
    @StringRes
    val categoryStringResId: Int,
    val homeChildList: List<HomeChildVE> = listOf()
)