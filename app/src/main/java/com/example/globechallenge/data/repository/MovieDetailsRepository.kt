package com.example.globechallenge.data.repository

import com.example.globechallenge.data.model.MovieDetails
import com.example.globechallenge.data.network.Api

class MovieDetailsRepository {

    private val service = Api.serviceMovieDetail()

    suspend fun getMovieDetail(id: String): MovieDetails {
        return service.getMovieDetail(id, Api.APIKEY).run {
            MovieDetails(title, genres, runtime, overview, releaseDate, originalLanguage, posterPath)
        }
    }
}