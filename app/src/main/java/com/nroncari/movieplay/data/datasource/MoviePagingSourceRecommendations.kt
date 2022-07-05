package com.nroncari.movieplay.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nroncari.movieplay.data.mapper.MovieToDomainMapper
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.model.MovieListItemDomain

class MoviePagingSourceRecommendations(
    private val service: MovieService
): PagingSource<Int, MovieListItemDomain>() {

    private val mapper = MovieToDomainMapper()
    var movieId: Long? = null
    private val _movieId: Long get() = movieId!!

    override fun getRefreshKey(state: PagingState<Int, MovieListItemDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemDomain> {
        return loadResults(params, _movieId)
    }

    private suspend fun loadResults(
        params: LoadParams<Int>,
        movieId: Long
    ): LoadResult.Page<Int, MovieListItemDomain> {
        val currentPage = params.key ?: 1
        val response = service.getMovieRecommendationsBy(movieId)

        return LoadResult.Page(
            data = response.results.map { mapper.map(it) },
            prevKey = null,
            nextKey = currentPage.takeIf { response.results.isNotEmpty() }?.inc()
        )
    }

}
