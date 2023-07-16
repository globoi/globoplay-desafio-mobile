package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.project.api.Genre
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.data.MovieDetailData
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup

interface MoviesRepository {
    suspend fun getMovies(): List<MoviesGroup>
    suspend fun getMovie(id: String): MovieDetailData?
    suspend fun getRelatedMovies(genres: List<Genre>): List<MovieCover>
    suspend fun getYoutubeKey(id: String): String?
}