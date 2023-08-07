package com.reisdeveloper.data

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideos
import com.reisdeveloper.data.repository.MovieRepository

class FakeMovieRepository : MovieRepository {

    val movieListMock = getMockMovieList()
    val movieDetails = getMockMovieDetails()
    val movieVideos = getMockMovieVideos()
    val favoriteMock = getMockFavorite()


    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getNowPlaying(language: String?, page: Int): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getPopularMovies(language: String?, page: Int): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getTopRatedMovies(language: String?, page: Int): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getUpcomingMovies(language: String?, page: Int): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getMyLists(): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getFavoriteMovies(): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getSimilarMovies(movieId: String, language: String?): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }

    override suspend fun getMovieDetails(language: String?, movieId: String): MovieDetails {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieDetails
    }

    override suspend fun favoriteMovie(favorite: Favorite) {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
    }

    override suspend fun getMovieVideos(movieId: String): MovieVideos {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieVideos
    }

    override suspend fun searchMovies(query: String): MovieList {
        if (shouldReturnError) {
            throw Throwable("Test exception")
        }
        return movieListMock
    }
}