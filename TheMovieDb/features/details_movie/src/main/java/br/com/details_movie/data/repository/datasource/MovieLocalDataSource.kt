package br.com.details_movie.data.repository.datasource

import br.com.local.model.movie_details.MovieEntity

interface MovieLocalDataSource {
    suspend fun getMovie(movieId: Int): MovieEntity?
    suspend fun insertMovieToDb(entity: MovieEntity)
    suspend fun clearAllMoviesFromDb()
}