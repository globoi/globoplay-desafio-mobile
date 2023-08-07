package com.reisdeveloper.data.repository

import com.reisdeveloper.data.BuildConfig
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
    suspend fun getMyLists(): MovieList
    suspend fun getFavoriteMovies(): MovieList
    suspend fun getSimilarMovies(movieId: String, language: String? = null): MovieList
    suspend fun getMovieDetails(language: String?, movieId: String): MovieDetails
    suspend fun favoriteMovie(favorite: Favorite)
    suspend fun getMovieVideos(movieId: String): MovieVideos
    suspend fun searchMovies(query: String): MovieList
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

    override suspend fun getMyLists(): MovieList {
        return movieApi.getMyLists(BuildConfig.ACCOUNT_ID)
    }

    override suspend fun getFavoriteMovies(): MovieList {
        return movieApi.getFavoriteMovies(BuildConfig.ACCOUNT_ID)
    }

    override suspend fun getSimilarMovies(movieId: String, language: String?): MovieList {
        return movieApi.getSimilarMovies(movieId, language)
    }

    override suspend fun getMovieDetails(language: String?, movieId: String): MovieDetails {
        return movieApi.getMovieDetails(movieId, language)
    }

    override suspend fun favoriteMovie(favorite: Favorite) {
        movieApi.favoriteMovie(BuildConfig.ACCOUNT_ID, favorite)
    }

    override suspend fun getMovieVideos(movieId: String): MovieVideos {
        return movieApi.getMovieVideos(movieId)
    }

    override suspend fun searchMovies(query: String): MovieList {
        return movieApi.searchMovies(null, query)
    }

}