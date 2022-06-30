package com.nroncari.movieplay.domain.repository

import androidx.paging.PagingData
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPagingMovies(genre: Int): Flow<PagingData<MovieListItemDomain>>

    suspend fun getMovieDetailBy(movieId: Long): MovieDetailDomain
}