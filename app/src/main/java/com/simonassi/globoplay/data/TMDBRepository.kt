package com.simonassi.globoplay.data

import com.simonassi.globoplay.api.TMDBService
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val service: TMDBService) {

    suspend fun getMovies(): List<Movie> {
        return withContext(Dispatchers.Default){
             val response = service.discoverMovies()
             response.results
        }
    }

    suspend fun getTvs(): List<Tv> {
        return withContext(Dispatchers.Default){
            val response = service.discoverTvs()
            response.results
        }
    }

    suspend fun getMovieById(id: Long): Movie {
        return withContext(Dispatchers.Default){
            service.findMovieById(id)
        }
    }

    suspend fun getTvById(id: Long): Tv {
        return withContext(Dispatchers.Default){
            service.findTvById(id)
        }
    }

    suspend fun getTrendingMovies(): List<Movie> {
        return withContext(Dispatchers.Default){
            val response = service.discoverTrendingMovies()
            response.results
        }
    }

    suspend fun getRelatedMovies(genreId: Long): List<Movie> {
        return withContext(Dispatchers.Default){
            val response = service.getRelatedMovies(genreId)
            response.results
        }
    }



}