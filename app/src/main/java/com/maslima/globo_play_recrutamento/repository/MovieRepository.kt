package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.Movie

interface MovieRepository {
    suspend fun search(
        page: String,
        query: String,
    ): List<Movie>

    suspend fun listMovies(
        page: String
    ): List<Movie>
}