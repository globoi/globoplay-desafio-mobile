package com.mazer.globoplayapp.data.datasource

import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie

interface MovieDataSource {
   suspend fun getPopularMoviesFromRemote(): List<Movie>
   suspend fun getTopRatedMoviesFromRemote(): List<Movie>
   suspend fun getUpcomingFromRemote(): List<Movie>
   suspend fun getGenreList(): List<Genre>

}