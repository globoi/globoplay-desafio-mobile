package com.ftoniolo.globoplay.framework

import androidx.paging.PagingSource
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.FilmsFromGenre
import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FilmsRemoteDataSource<FilmsDataWrapperResponse>
) : FilmsRepository {
    override fun getFilms(query: String): PagingSource<Int, FilmsFromGenre> {
        return FilmsPaging()
    }
}