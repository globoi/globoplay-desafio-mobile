package com.ftoniolo.testing.model

import com.ftoniolo.core.domain.model.Film

@Suppress("MagicNumber")
class FilmsFactory {

    fun create(movie: Movie) = when(movie) {
        Movie.Sonic -> Film(
            id = 1L, overview = "Sonic Overview Teste", title = "Sonic",
            genreIds = listOf(9L, 8L, 7L), imageUrl = "https://sonic.com.br/imagem",
            releaseDate = "01/01/2022", originalLanguage = "pt-BR"
        )
        Movie.Batman -> Film(
            id = 1L, overview = "Batman Overview Teste", title = "Batman",
            genreIds = listOf(3L, 7L, 2L), imageUrl = "https://batman.com.br/imagem",
            releaseDate = "22/01/2022", originalLanguage = "pt-BR"
        )
    }

    sealed class Movie {
        object Sonic : Movie()
        object Batman : Movie()
    }
}