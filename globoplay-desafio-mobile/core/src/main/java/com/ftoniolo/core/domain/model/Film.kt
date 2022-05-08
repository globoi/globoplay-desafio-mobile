package com.ftoniolo.core.domain.model

data class Film (
    val id: Long,
    val overview: String,
    val title: String,
    val genreIds: List<Long>,
    val imageUrl: String,
    val releaseDate: String,
    val originalLanguage: String
)