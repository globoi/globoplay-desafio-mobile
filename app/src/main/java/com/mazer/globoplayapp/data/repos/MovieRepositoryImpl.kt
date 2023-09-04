package com.mazer.globoplayapp.data.repos

import androidx.lifecycle.LiveData
import com.mazer.globoplayapp.data.datasource.MovieDataSource
import com.mazer.globoplayapp.data.local.dao.MovieDao
import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource, private val movieDao: MovieDao) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return movieDataSource.getPopularMoviesFromRemote()
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return movieDataSource.getTopRatedMoviesFromRemote()
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return movieDataSource.getUpcomingFromRemote()
    }

    override suspend fun getGenreList(): List<Genre> {
        return movieDataSource.getGenreList()
    }

    override suspend fun getRecommendationList(movieId: Int): List<Movie> {
        return movieDataSource.getRecommendationList(movieId)
    }

    override suspend fun addToFavorites(movie: Movie) {
        movieDao.saveFavoriteMovie(movie)
    }

    override suspend fun deleteFromFavorites(movie: Movie) {
        movieDao.deleteFavoriteMovie(movie)
    }

    override suspend fun getAllFavorites(): LiveData<List<Movie>> {
        return movieDao.getMyFavoritesMovies()
    }

    override suspend fun getFavoriteMovie(movieId: Int): Movie? {
        return movieDao.getFavoriteMovie(movieId)
    }
}