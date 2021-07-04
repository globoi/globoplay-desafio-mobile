package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.Movie

interface MovieRepository {
    suspend fun listMovies(
        page: Int,
        query: String?,
        categoryId: Int? = 0
    ): List<Movie>
}