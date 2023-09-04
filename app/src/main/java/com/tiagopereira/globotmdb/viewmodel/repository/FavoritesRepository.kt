package com.tiagopereira.globotmdb.viewmodel.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tiagopereira.globotmdb.database.dao.MovieDao
import com.tiagopereira.globotmdb.paging.MovieDaoPagingSource

class FavoritesRepository constructor(private val movieDao: MovieDao) {

    suspend fun getCountFavorites() = movieDao.getCount()

    fun getFavoriteMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 1000,
                enablePlaceholders = false),
            pagingSourceFactory = {
                MovieDaoPagingSource(movieDao)
            }
        ).liveData
}