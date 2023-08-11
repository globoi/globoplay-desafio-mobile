package br.com.details_movie.data.repository.datasource

import br.com.local.model.movie_details.MovieDetailsEntity

interface MovieLocalDataSource {
    suspend fun getMovie(movieId: Int): MovieDetailsEntity?
    suspend fun insertMovieToDb(entity: MovieDetailsEntity)
    suspend fun clearAllMoviesFromDb()
}