package com.ftoniolo.testing.model

import com.ftoniolo.core.domain.model.Film

class FilmFactory {
    fun create(film: FakeFilm) = when(film) {
        FilmFactory.FakeFilm.FakeFilm1 -> Film(
            id = 1L,
            imageUrl = "https://sonic.com.br/imagem",
            overview = "Descrição fake do filme Sonic",
            title = "Sonic",
            genreIds = listOf(1L,2L),
            releaseDate = "2022-03-03",
            originalLanguage = "pt-BR"
        )
    }

    sealed class FakeFilm {
        object FakeFilm1: FakeFilm()
    }
}