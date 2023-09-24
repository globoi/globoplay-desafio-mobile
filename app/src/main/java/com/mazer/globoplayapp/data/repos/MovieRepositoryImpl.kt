package com.mazer.globoplayapp.data.repos

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mazer.globoplayapp.data.datasource.RemoteMovieDataSource
import com.mazer.globoplayapp.data.local.dao.MovieDao
import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video

class MovieRepositoryImpl(private val remoteMovieDataSource: RemoteMovieDataSource, private val movieDao: MovieDao) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return remoteMovieDataSource.getPopularMoviesFromRemote(page)
    }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        return remoteMovieDataSource.getTopRatedMoviesFromRemote(page)
    }

    override suspend fun getUpcomingMovies(page: Int): List<Movie> {
        return remoteMovieDataSource.getUpcomingFromRemote(page)
    }

    override suspend fun getGenreList(): List<Genre> {
        return remoteMovieDataSource.getGenreList()
    }

    override suspend fun getRecommendationList(movieId: Int): List<Movie> {
        return remoteMovieDataSource.getRecommendationList(movieId)
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

    override suspend fun getVideoList(movieId: Int): List<Video?> {
        return remoteMovieDataSource.getVideoList(movieId)
    }
}