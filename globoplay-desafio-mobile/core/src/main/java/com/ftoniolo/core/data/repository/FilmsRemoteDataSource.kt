package com.ftoniolo.core.data.repository

interface FilmsRemoteDataSource<T> {

    suspend fun fetchFilms(queries: Map<String, String>): T
}