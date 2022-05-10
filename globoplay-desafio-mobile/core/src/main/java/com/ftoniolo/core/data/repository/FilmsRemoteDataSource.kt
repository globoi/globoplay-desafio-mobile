package com.ftoniolo.core.data.repository

import com.ftoniolo.core.domain.model.FilmPaging
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.domain.model.WatchTooPaging

interface FilmsRemoteDataSource {

    suspend fun fetchFilms(queries: Map<String, String>): FilmPaging

    suspend fun fetchWatchToo(filmId: Long, queries: Map<String, String>): WatchTooPaging

//    suspend fun fetchWatchToo(filmId: Long): List<WatchToo>
}