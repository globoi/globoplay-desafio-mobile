package com.mazer.globoplayapp.data.datasource

import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video

interface RemoteMovieDataSource {
    suspend fun getPopularMoviesFromRemote(page: Int): List<Movie>
    suspend fun getTopRatedMoviesFromRemote(page: Int): List<Movie>
    suspend fun getUpcomingFromRemote(page: Int): List<Movie>
    suspend fun getGenreList(): List<Genre>
    suspend fun getRecommendationList(movieId: Int): List<Movie>
    suspend fun getVideoList(movieId: Int): List<Video?>

}