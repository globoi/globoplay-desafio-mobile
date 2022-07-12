package com.nroncari.movieplay.data.repository

import com.nroncari.movieplay.data.localdatasource.MovieLocalDataSource
import com.nroncari.movieplay.data.model.MovieDTO
import com.nroncari.movieplay.domain.repository.MovieLocalRepository

class MovieLocalRepositoryImpl(
    private val dataSource: MovieLocalDataSource
): MovieLocalRepository {

    override fun insert(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        dataSource.insert(movie, inFailureCase, inSuccessCase)

    override fun listAll() = dataSource.listAll()

    override suspend fun returnById(movieId: Long) = dataSource.returnById(movieId)

    override fun delete(movie: MovieDTO, inFailureCase: () -> Unit, inSuccessCase: () -> Unit) =
        dataSource.delete(movie, inFailureCase, inSuccessCase)
}