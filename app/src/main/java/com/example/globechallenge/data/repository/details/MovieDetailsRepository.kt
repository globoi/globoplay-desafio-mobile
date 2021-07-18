package com.example.globechallenge.data.repository.details

import com.example.globechallenge.data.model.details.MovieCast
import com.example.globechallenge.data.model.details.MovieDetails
import com.example.globechallenge.data.network.Api

class MovieDetailsRepository {

    private val service = Api.serviceMovieDetail()

    suspend fun getMovieDetail(id: String): MovieDetails {
        return service.getMovieDetails(id, Api.APIKEY).run {
            MovieDetails(title, genres, runtime, overview,
                releaseDate, productionCountries[0]?.name ?: "", posterPath)
        }
    }

    suspend fun getMovieCreditToGetCast(id: String): MovieCast {
        return service.getMovieCreditToGetCast(id, Api.APIKEY).run {
            MovieCast(cast)
        }
    }
}