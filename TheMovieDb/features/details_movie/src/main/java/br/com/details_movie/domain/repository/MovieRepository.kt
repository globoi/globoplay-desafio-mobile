package br.com.details_movie.domain.repository

import br.com.details_movie.domain.model.Movie
import br.com.network.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovie(movieId: Int): Flow<Resource<Movie>>
}