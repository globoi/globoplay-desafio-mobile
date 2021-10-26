package com.com.ifood.repository

import com.com.ifood.repository.model.MoviesResponse
import com.com.ifood.repository.model.MoviesTitleCategory

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