package com.reisdeveloper.data.repository

import com.reisdeveloper.data.api.MovieApi
import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideos

interface MovieRepository {
    suspend fun getNowPlaying(language: String?, page: Int): MovieList
    suspend fun getPopularMovies(language: String?, page: Int): MovieList
    suspend fun getTopRatedMovies(language: String?, page: Int): MovieList
    suspend fun getUpcomingMovies(language: String?, page: Int): MovieList
    suspend fun getMyLists(accountId: String): MovieList
    suspend fun getFavoriteMovies(accountId: String): MovieList
    suspend fun getSimilarMovies(movieId: String): MovieList
    suspend fun getMovieDetails(language: String?, movieId: String): MovieDetails
    suspend fun favoriteMovie(accountId: String, favorite: Favorite)
    suspend fun getMovieVideos(movieId: String): MovieVideos
}

class ListsRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {
    override suspend fun getNowPlaying(language: String?, page: Int): MovieList {
        return movieApi.getNowPlaying(language, page)
    }

    override suspend fun getPopularMovies(language: String?, page: Int): MovieList {
        return movieApi.getPopularMovies(language, page)
    }

    override suspend fun getTopRatedMovies(language: String?, page: Int): MovieList {
        return movieApi.getTopRatedMovies(language, page)
    }

    override suspend fun getUpcomingMovies(language: String?, page: Int): MovieList {
        return movieApi.getUpcomingMovies(language, page)
    }

    override suspend fun getMyLists(accountId: String): MovieList {
        return movieApi.getMyLists(accountId)
    }

    override suspend fun getFavoriteMovies(accountId: String): MovieList {
        return movieApi.getFavoriteMovies(accountId)
    }

    override suspend fun getSimilarMovies(movieId: String): MovieList {
        return movieApi.getSimilarMovies(movieId)
    }

    override suspend fun getMovieDetails(language: String?, movieId: String): MovieDetails {
        return movieApi.getMovieDetails(language, movieId)
    }

    override suspend fun favoriteMovie(accountId: String, favorite: Favorite) {
        movieApi.favoriteMovie(accountId, favorite)
    }

    override suspend fun getMovieVideos(movieId: String): MovieVideos {
        return movieApi.getMovieVideos(movieId)
    }

}