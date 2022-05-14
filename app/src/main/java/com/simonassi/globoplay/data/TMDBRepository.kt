package com.simonassi.globoplay.data

import com.simonassi.globoplay.api.TMDBService
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

}