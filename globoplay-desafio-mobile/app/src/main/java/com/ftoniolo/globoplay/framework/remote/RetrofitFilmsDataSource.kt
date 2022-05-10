package com.ftoniolo.globoplay.framework.remote

import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.FilmPaging
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.domain.model.WatchTooPaging
import com.ftoniolo.globoplay.framework.network.TmdbApi
import com.ftoniolo.globoplay.framework.network.response.film.toFilmModel
import com.ftoniolo.globoplay.framework.network.response.watchtoo.toWatchTooModel
import javax.inject.Inject

class RetrofitFilmsDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : FilmsRemoteDataSource {
    override suspend fun fetchFilms(queries: Map<String, String>): FilmPaging {

        val data = tmdbApi.getFilms(queries)
        val films = data.results.map {
            it.toFilmModel()
        }

        return FilmPaging(
            page = data.page,
            totalPages = data.totalPages,
            films = films
        )
    }

    override suspend fun fetchWatchToo(filmId: Long, queries: Map<String, String>): WatchTooPaging {
        val data = tmdbApi.getWatchToo(filmId, queries)
        val films = data.results.map {
            it.toWatchTooModel()
        }

        return WatchTooPaging(
            page = data.page,
            totalPages = data.totalPages,
            films = films
        )
    }

//    override suspend fun fetchWatchToo(filmId: Long): List<WatchToo> {
//        return tmdbApi.getWatchToo(filmId).results.map {
//            it.toWatchTooModel()
//        }
//    }
}