package com.ftoniolo.core.domain.model

data class FilmsFromGenre(
    val genre: String,
    val rvHorizontal: List<Film>
)
