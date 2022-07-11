package com.nroncari.movieplay.domain.repository

import androidx.paging.PagingData
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPagingMoviesByGenre(genre: Int): Flow<PagingData<MovieListItemDomain>>

    fun getPagingMovieRecommendationsBy(movieId: Long) : Flow<PagingData<MovieListItemDomain>>

    fun getPagingMovieByKeyword(keyword: String) : Flow<PagingData<MovieListItemDomain>>

    suspend fun getMovieDetailBy(movieId: Long): MovieDetailDomain

    suspend fun getMovieDataVideo(movieId: Long): List<MovieDataVideoDomain>
}