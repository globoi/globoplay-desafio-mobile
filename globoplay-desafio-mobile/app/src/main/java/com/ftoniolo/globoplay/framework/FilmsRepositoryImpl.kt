package com.ftoniolo.globoplay.framework

import androidx.paging.PagingSource
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.globoplay.framework.paging.WatchTooPagingSource
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FilmsRemoteDataSource
) : FilmsRepository {
    override suspend fun getPopularFilms(): List<Film> {
        return remoteDataSource.fetchPopularFilms()
    }

    override suspend fun getMoviesByCategory(genreId: Long): List<Film> {
        return remoteDataSource.fetchMoviesByCategory(genreId)
    }

    override fun getWatchToo(filmId: Long): PagingSource<Int, WatchToo> {
        return WatchTooPagingSource(remoteDataSource, filmId)
    }
}