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
        Movie.HomemAranha -> Film(
            id = 1L, overview = "Homem Aranha Overview", title = "Homem Aranha",
            genreIds = listOf(9L, 8L, 7L), imageUrl = "https://image.tmdb.org/t/p/w500/imagem-homem-aranha",
            releaseDate = "01/01/2022", originalLanguage = "pt-BR"
        )
        Movie.DrEstranho -> Film(
            id = 1L, overview = "Dr Estranho Overview", title = "Dr Estranho ",
            genreIds = listOf(91L, 80L, 17L), imageUrl = "https://image.tmdb.org/t/p/w500/imagem-dr-estranho",
            releaseDate = "21/04/2022", originalLanguage = "pt-BR"
        )
    }

    sealed class Movie {
        object Sonic : Movie()
        object Batman : Movie()
        object HomemAranha : Movie()
        object DrEstranho : Movie()
    }
}


/*
listOf(
            FilmResponse(
                id = 1L, overview = "Homem Aranha Overview", title = "Homem Aranha",
                genreIds = listOf(9L, 8L, 7L), posterPath = "/imagem-homem-aranha",
                releaseDate = "01/01/2022", originalLanguage = "pt-BR"
            ),
            FilmResponse(
                id = 1L, overview = "Dr Estranho Overview", title = "Dr Estranho ",
                genreIds = listOf(91L, 80L, 17L), posterPath = "/imagem-dr-estranho ",
                releaseDate = "21/04/2022", originalLanguage = "pt-BR"
            ),
        )
 */