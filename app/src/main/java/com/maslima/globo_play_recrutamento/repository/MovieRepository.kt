package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.Movie

interface MovieRepository {
    suspend fun listMovies(
        page: Int,
        query: String?,
        categoryId: Int? = 0
    ): List<Movie>

    suspend fun getMovie(
        movieId: Int,
    ): Movie?

    suspend fun insertFavorite(movie: Movie)

    suspend fun listFavorites(): List<Movie>

    suspend fun getFavorite(id: Int): Movie

    suspend fun deleteFavorite(movie: Movie)
}