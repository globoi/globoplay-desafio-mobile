package com.ftoniolo.core.domain.model

data class FilmPaging(
    val page: Long,
    val totalPages: Long,
    val films: List<Film>
)