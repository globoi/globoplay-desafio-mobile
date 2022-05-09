package com.ftoniolo.globoplay.framework.remote

import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.FilmPaging
import com.ftoniolo.globoplay.framework.network.TmdbApi
import com.ftoniolo.globoplay.framework.network.response.film.toFilmModel
import javax.inject.Inject

class RetrofitFilmsDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : FilmsRemoteDataSource {
    override suspend fun fetchFilms(queries: Map<String, String>): FilmPaging {

        val data= tmdbApi.getFilms(queries)
        val films = data.results.map {
            it.toFilmModel()
        }

        return FilmPaging(
            page = data.page,
            totalPages = data.totalPages,
            films = films
        )
    }
}