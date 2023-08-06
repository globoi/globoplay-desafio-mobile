package com.reisdeveloper.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reisdeveloper.data.dataModel.Movie
import com.reisdeveloper.data.repository.MovieRepository
import kotlin.math.max

class MoviePagingSource(
    private val movieRepository: MovieRepository,
    private val movieListType: MovieListType
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: STARTING_KEY

        val range = pageIndex.until(pageIndex + params.loadSize)
        return try {
            val response = when (movieListType) {
                MovieListType.NOW_PLAYING ->
                    movieRepository.getNowPlaying(DEFAULT_LANGUAGE, pageIndex)

                MovieListType.POPULAR ->
                    movieRepository.getPopularMovies(DEFAULT_LANGUAGE, pageIndex)

                MovieListType.TOP_RATED ->
                    movieRepository.getTopRatedMovies(DEFAULT_LANGUAGE, pageIndex)

                MovieListType.UPCOMING ->
                    movieRepository.getUpcomingMovies(DEFAULT_LANGUAGE, pageIndex)
            }

            LoadResult.Page(
                data = response.results,
                prevKey = when (pageIndex) {
                    STARTING_KEY -> null
                    else -> when (val prevKey =
                        ensureValidKey(key = range.first - params.loadSize)) {
                        STARTING_KEY -> null
                        else -> prevKey
                    }
                },
                nextKey = range.last + 1
            )
        } catch (exception: Throwable) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)


    companion object {
        const val STARTING_KEY = 1
        const val DEFAULT_LANGUAGE = "pt-BR"
    }
}