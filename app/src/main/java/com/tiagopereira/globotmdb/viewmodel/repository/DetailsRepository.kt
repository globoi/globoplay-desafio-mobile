package com.tiagopereira.globotmdb.viewmodel.repository

import androidx.annotation.WorkerThread
import com.tiagopereira.globotmdb.database.dao.MovieDao
import com.tiagopereira.globotmdb.database.entities.Movie
import com.tiagopereira.globotmdb.utils.Constants.Companion.KEY_API
import com.tiagopereira.globotmdb.utils.Constants.Companion.LANGUAGE
import com.tiagopereira.globotmdb.utils.RetrofitService

class DetailsRepository constructor(private val retrofitService: RetrofitService, private val movieDao: MovieDao) {

    suspend fun getMovieDetails(id: Int) = retrofitService.getMovieDetails(id, KEY_API, LANGUAGE)

    @WorkerThread
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    @WorkerThread
    suspend fun isFavorite(idMovie: Int): Movie? {
        return movieDao.getMovieById(idMovie)
    }

    @WorkerThread
    suspend fun removeById(idMovie: Int) {
        return movieDao.removeById(idMovie)
    }
}