package com.example.globechallenge.data.repository.details

import com.example.globechallenge.data.model.details.MovieDetails
import com.example.globechallenge.data.network.Api

class MovieDetailsRepository {

    private val service = Api.serviceMovieDetail()

    suspend fun getMovieDetail(id: String): MovieDetails {
        return service.getMovieDetail(id, Api.APIKEY).run {
            MovieDetails(title, genres, runtime, overview, releaseDate, originalLanguage, posterPath)
        }
    }
}