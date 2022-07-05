package com.nroncari.movieplay.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nroncari.movieplay.data.datasource.Genre.ACTION
import com.nroncari.movieplay.data.mapper.MovieToDomainMapper
import com.nroncari.movieplay.data.service.MovieService
import com.nroncari.movieplay.domain.model.MovieListItemDomain

class MoviePagingSourceByGenre(
    private val service: MovieService
) : PagingSource<Int, MovieListItemDomain>() {

    private val mapper = MovieToDomainMapper()
    var genre: Int = ACTION
    private val _genre: Int get() = genre

    override fun getRefreshKey(state: PagingState<Int, MovieListItemDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemDomain> {
        return loadResults(params, _genre)
    }

    private suspend fun loadResults(
        params: LoadParams<Int>,
        genre: Int
    ): LoadResult.Page<Int, MovieListItemDomain> {
        val currentPage = params.key ?: 1
        val response = service.getMoviesByGenre(page = currentPage, genre = genre)

        return LoadResult.Page(
            data = response.results.map { mapper.map(it) },
            prevKey = null,
            nextKey = currentPage.takeIf { response.results.isNotEmpty() }?.inc()
        )
    }
}

object Genre {
    const val ACTION = 28
    const val ANIMATION = 16
    const val COMEDY = 35
    const val DRAMA = 18
    const val HORROR = 27
}