package com.mazer.globoplayapp.data.repos

import androidx.lifecycle.LiveData
import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getGenreList(): List<Genre>
    suspend fun getRecommendationList(movieId: Int): List<Movie>
    suspend fun addToFavorites(movie: Movie)
    suspend fun deleteFromFavorites(movie: Movie)
    suspend fun getAllFavorites(): LiveData<List<Movie>>
    suspend fun getFavoriteMovie(movieId: Int): Movie?
}