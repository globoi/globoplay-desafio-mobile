package com.com.globo.repository

import com.com.globo.repository.model.MoviesResponse
import com.com.globo.repository.model.MoviesTitleCategory

class MoviesCacheRepository private constructor() {

    companion object {
        private val moviesCacheRepository =
            MoviesCacheRepository()
        fun getInstance() = moviesCacheRepository
    }
    private val data: MutableMap<MoviesTitleCategory, MoviesResponse> = mutableMapOf()

    fun addMovie(movieTitleCategory: MoviesTitleCategory, movieResponse: MoviesResponse) {
        data[movieTitleCategory] = movieResponse
    }

    fun contains(movie: MoviesTitleCategory): Boolean {
        return data.contains(movie)
    }

    fun getMovies(movie: MoviesTitleCategory): MoviesResponse {
        return data[movie]!!
    }
}