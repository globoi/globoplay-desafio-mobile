package com.example.globechallenge.data.repository.details

import com.example.globechallenge.data.model.models.details.MovieCast
import com.example.globechallenge.data.model.models.details.MovieDetails
import com.example.globechallenge.data.model.models.details.MovieVideos

interface MovieDetailsRepository {
    suspend fun getMovieDetail(id: String): MovieDetails
    suspend fun getMovieCreditToGetCast(id: String): MovieCast
    suspend fun getMovieVideos(id: String): MovieVideos
}

