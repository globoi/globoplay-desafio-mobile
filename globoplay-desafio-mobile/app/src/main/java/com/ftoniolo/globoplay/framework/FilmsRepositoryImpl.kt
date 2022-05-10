package com.ftoniolo.globoplay.framework

import androidx.paging.PagingSource
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.globoplay.framework.paging.FilmsPagingSource
import com.ftoniolo.globoplay.framework.paging.WatchTooPagingSource
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FilmsRemoteDataSource
) : FilmsRepository {
    override fun getFilms(): PagingSource<Int, Film> {
        return FilmsPagingSource(remoteDataSource)
    }

    override fun getWatchToo(filmId: Long): PagingSource<Int, WatchToo> {
        return WatchTooPagingSource(remoteDataSource, filmId)
    }
}