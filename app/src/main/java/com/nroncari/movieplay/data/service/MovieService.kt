package com.nroncari.movieplay.data.service

import com.nroncari.movieplay.data.model.BaseMovieListResponse
import com.nroncari.movieplay.data.model.MovieListItemResponse

interface MovieService {

    // TODO GET()
    suspend fun getMoviesByGenre(page: Int, genre: Int): BaseMovieListResponse<MovieListItemResponse>
}