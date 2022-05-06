package com.ftoniolo.globoplay.framework.remote

import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.globoplay.framework.network.TmdbApi
import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse
import javax.inject.Inject

class RetrofitFilmsDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : FilmsRemoteDataSource<FilmsDataWrapperResponse> {
    override suspend fun fetchFilms(queries: Map<String, String>): FilmsDataWrapperResponse {
        return tmdbApi.getFilms(queries)
    }
}