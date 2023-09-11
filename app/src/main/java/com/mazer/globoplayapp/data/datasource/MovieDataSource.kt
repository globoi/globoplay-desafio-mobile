package com.mazer.globoplayapp.data.datasource

import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video

interface MovieDataSource {
   suspend fun getPopularMoviesFromRemote(): List<Movie>
   suspend fun getTopRatedMoviesFromRemote(): List<Movie>
   suspend fun getUpcomingFromRemote(): List<Movie>
   suspend fun getGenreList(): List<Genre>
   suspend fun getRecommendationList(movieId: Int): List<Movie>
   suspend fun getVideoList(movieId: Int): List<Video?>

}