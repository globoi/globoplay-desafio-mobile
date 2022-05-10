package com.ftoniolo.core.data.repository

import androidx.paging.PagingSource
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.WatchToo

interface FilmsRepository {

    suspend fun getPopularFilms(): List<Film>

    suspend fun getMoviesByCategory(genreId: Long): List<Film>

    fun getWatchToo(filmId: Long): PagingSource<Int, WatchToo>
}