package com.nroncari.movieplay.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nroncari.movieplay.data.mapper.MovieToDomainMapper
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.model.MovieListItemDomain

class MoviePagingSource(
    private val service: MovieService
) : PagingSource<Int, MovieListItemDomain>() {

    private val mapper = MovieToDomainMapper()

    override fun getRefreshKey(state: PagingState<Int, MovieListItemDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemDomain> {
        val currentPage = params.key ?: 1
        val response = service.getMoviesByGenre(page = currentPage, genre = 28)

        return LoadResult.Page(
            data = response.results.map { mapper.map(it) },
            prevKey = null,
            nextKey = currentPage.takeIf { response.results.isNotEmpty() }?.inc()
        )
    }
}