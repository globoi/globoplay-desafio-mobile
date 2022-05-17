package com.ftoniolo.globoplay.framework.remote

import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.Trailer
import com.ftoniolo.core.domain.model.WatchTooPaging
import com.ftoniolo.globoplay.framework.network.TmdbApi
import com.ftoniolo.globoplay.framework.network.response.film.toFilmModel
import com.ftoniolo.globoplay.framework.network.response.trailer.toTrailer
import com.ftoniolo.globoplay.framework.network.response.watchtoo.toWatchTooModel
import javax.inject.Inject

class RetrofitFilmsDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : FilmsRemoteDataSource {
    override suspend fun fetchPopularFilms(): List<Film> {
        return tmdbApi.getPopularFilms().results.map {
            it.toFilmModel()
        }
    }

    override suspend fun fetchMoviesByCategory(genreId: Long): List<Film> {
        return tmdbApi.getMoviesByCategory(genreId).results.map {
            it.toFilmModel()
        }
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

    override suspend fun fetchTrailerById(filmId: Long): List<Trailer> {
        return tmdbApi.getTrailerById(filmId).results.map {
            it.toTrailer()
        }
    }
}