package com.reisdeveloper.data.repository

import com.reisdeveloper.data.api.MovieApi
import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideos

interface ListsRepository {
    suspend fun getMyLists(accountId: String): MovieList
    suspend fun getFavoriteMovies(accountId: String): MovieList
    suspend fun getSimilarMovies(movieId: String): MovieList
    suspend fun getMovieDetails(movieId: String): MovieDetails
    suspend fun favoriteMovie(accountId: String, favorite: Favorite)
    suspend fun getMovieVideos(movieId: String): MovieVideos
}

class ListsRepositoryImpl(
    private val movieApi: MovieApi
): ListsRepository {

    override suspend fun getMyLists(accountId: String): MovieList {
        return movieApi.getMyLists(accountId)
    }

    override suspend fun getFavoriteMovies(accountId: String): MovieList {
        return movieApi.getFavoriteMovies(accountId)
    }

    override suspend fun getSimilarMovies(movieId: String): MovieList {
        return movieApi.getSimilarMovies(movieId)
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetails {
        return movieApi.getMovieDetails(movieId)
    }

    override suspend fun favoriteMovie(accountId: String, favorite: Favorite) {
        movieApi.favoriteMovie(accountId, favorite)
    }

    override suspend fun getMovieVideos(movieId: String): MovieVideos {
        return movieApi.getMovieVideos(movieId)
    }

}