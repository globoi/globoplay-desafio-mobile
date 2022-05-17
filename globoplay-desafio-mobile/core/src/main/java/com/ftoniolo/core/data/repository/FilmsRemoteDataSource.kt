package com.ftoniolo.core.data.repository

import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.Trailer
import com.ftoniolo.core.domain.model.WatchTooPaging

interface FilmsRemoteDataSource {

    suspend fun fetchPopularFilms(): List<Film>

    suspend fun fetchMoviesByCategory(genreId: Long): List<Film>

    suspend fun fetchWatchToo(filmId: Long, queries: Map<String, String>): WatchTooPaging

    suspend fun fetchTrailerById(filmId: Long): List<Trailer>
}