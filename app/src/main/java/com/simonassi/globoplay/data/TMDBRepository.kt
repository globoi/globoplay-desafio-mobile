package com.simonassi.globoplay.data

import android.content.Context
import com.simonassi.globoplay.api.TMDBService
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.utilities.UrlExtractorCallback
import com.simonassi.globoplay.utilities.WatchUrlExtractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val service: TMDBService) {

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

    suspend fun searchMoviesByKeyword(key: String): List<Movie> {
        return withContext(Dispatchers.Default){
            val response = service.searchMoviesByKeyword(keyword = key)
            response.results
        }
    }

    suspend fun getTvVideoKey(tvId: Long): String {
        return withContext(Dispatchers.Default){
            val response = service.getTvVideoKey(tvId)
            if(PERMITTED_PROVIDERS.contains(response.results[0].site)){
                response.results[0].key
            }else{
                ""
            }
        }
    }

    suspend fun getMovieVideoKey(movieId: Long): String {
        return withContext(Dispatchers.Default){
            val response = service.getMovieVideoKey(movieId)
            if(PERMITTED_PROVIDERS.contains(response.results[0].site)){
                response.results[0].key
            }else{
                ""
            }
        }
    }

    companion object {
        val PERMITTED_PROVIDERS = listOf("YouTube")
    }

}