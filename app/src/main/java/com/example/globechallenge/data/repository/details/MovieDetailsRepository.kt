package com.example.globechallenge.data.repository.details

import com.example.globechallenge.data.model.models.details.MovieCast
import com.example.globechallenge.data.model.models.details.MovieDetails
import com.example.globechallenge.data.model.models.details.MovieVideos
import com.example.globechallenge.data.network.Api

class MovieDetailsRepository {

    private val service = Api.serviceMovie()

    suspend fun getMovieDetail(id: String): MovieDetails {
        return service.getMovieDetails(id, Api.API_KEY).run {
            MovieDetails(
                title, genres, runtime, overview,
                releaseDate, productionCountries[0]?.name ?: "", posterPath
            )
        }
    }

    suspend fun getMovieCreditToGetCast(id: String): MovieCast {
        return service.getMovieCreditToGetCast(id, Api.API_KEY).run {
            MovieCast(cast)
        }
    }

    suspend fun getMovieVideos(id: String): MovieVideos {
        return service.getMovieVideos(id, Api.API_KEY).run {
            MovieVideos(this.id, results)
        }
    }
}