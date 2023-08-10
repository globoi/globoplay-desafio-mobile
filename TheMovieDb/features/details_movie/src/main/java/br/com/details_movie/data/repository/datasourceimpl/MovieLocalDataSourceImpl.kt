package br.com.details_movie.data.repository.datasourceimpl

import br.com.details_movie.data.repository.datasource.MovieLocalDataSource
import br.com.local.dao.movie_details.MovieDetailsDao
import br.com.local.model.movie_details.MovieEntity
import javax.inject.Inject


class MovieLocalDataSourceImpl @Inject constructor (
    private val moviesDao: MovieDetailsDao
        ) : MovieLocalDataSource {

    override suspend fun getMovie(movieId: Int): MovieEntity?  = moviesDao.getMovie(movieId)

    override suspend fun insertMovieToDb(entity: MovieEntity) {
        moviesDao.upsert(entity)
    }

    override suspend fun clearAllMoviesFromDb() {
        moviesDao.clearAll()
    }


}