package com.mazer.globoplayapp.data.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
    suspend fun getTopRatedMovies(page: Int): List<Movie>
    suspend fun getUpcomingMovies(page: Int): List<Movie>
    suspend fun getGenreList(): List<Genre>
    suspend fun getRecommendationList(movieId: Int): List<Movie>
    suspend fun addToFavorites(movie: Movie)
    suspend fun deleteFromFavorites(movie: Movie)
    suspend fun getAllFavorites(): LiveData<List<Movie>>
    suspend fun getFavoriteMovie(movieId: Int): Movie?
    suspend fun getVideoList(movieId: Int): List<Video?>
}