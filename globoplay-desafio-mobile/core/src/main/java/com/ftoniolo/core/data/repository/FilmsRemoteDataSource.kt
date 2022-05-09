package com.ftoniolo.core.data.repository

import com.ftoniolo.core.domain.model.FilmPaging

interface FilmsRemoteDataSource {

    suspend fun fetchFilms(queries: Map<String, String>): FilmPaging
}