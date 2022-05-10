package com.ftoniolo.factory.response

import com.ftoniolo.globoplay.framework.network.response.film.FilmResponse
import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse

class FilmsDataWrapperResponseFactory {

    fun create() = FilmsDataWrapperResponse(
        page = 1L,
        results = listOf<FilmResponse>(
            FilmResponse(
                id = 1L, overview = "Homem Aranha Overview", title = "Homem Aranha",
                genreIds = listOf(9L, 8L, 7L), posterPath = "/imagem-homem-aranha",
                releaseDate = "01/01/2022", originalLanguage = "pt-BR"
            ),
            FilmResponse(
                id = 1L, overview = "Dr Estranho Overview", title = "Dr Estranho ",
                genreIds = listOf(91L, 80L, 17L), posterPath = "/imagem-dr-estranho",
                releaseDate = "21/04/2022", originalLanguage = "pt-BR"
            ),
        ),
        totalPages = 1L,
        totalResults = 2L
    )
}