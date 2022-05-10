package com.ftoniolo.testing.model

import com.ftoniolo.core.domain.model.WatchToo

@Suppress("MagicNumber")
class WatchTooFactory {

    fun create(movie: Movie) = when(movie) {
        Movie.Sonic -> WatchToo(
            id = 1L,
            imageUrl = "https://sonic.com.br/imagem"
            )
        Movie.Batman -> WatchToo(
            id = 1L,
            imageUrl = "https://batman.com.br/imagem",
        )
        Movie.HomemAranha -> WatchToo(
            id = 1L,
            imageUrl = "imagem-homem-aranha",
        )
        Movie.DrEstranho -> WatchToo(
            id = 1L,
            imageUrl = "imagem-dr-estranho",
        )
    }

    sealed class Movie {
        object Sonic : Movie()
        object Batman : Movie()
        object HomemAranha : Movie()
        object DrEstranho : Movie()
    }
}