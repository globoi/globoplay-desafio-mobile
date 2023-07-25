package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.project.api.Genre
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup

interface MoviesRepository {
    suspend fun getDiscoverMovies(): List<MoviesGroup>
    suspend fun getMovie(id: String): MovieDetailData?
    suspend fun getRelatedMovies(genres: List<Genre>): List<MovieCover>
    suspend fun getYoutubeKey(id: String): String?
    suspend fun addFavorite(movieCover: MovieCover)
    suspend fun removeFavorite(movieCover: MovieCover)
    suspend fun getCurrentFavorites(): List<MoviesGroup>
    suspend fun searchVideos(query: String): List<MoviesGroup>
}