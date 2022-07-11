package com.nroncari.movieplay.data.remotedatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nroncari.movieplay.data.mapper.MovieToDomainMapper
import com.nroncari.movieplay.data.remotedatasource.service.MovieRemoteService
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSourceRecommendations(
    private val service: MovieRemoteService
) : PagingSource<Int, MovieListItemDomain>() {

    private val mapper = MovieToDomainMapper()
    var movieId: Long? = null
    private val _movieId: Long get() = movieId!!

    override fun getRefreshKey(state: PagingState<Int, MovieListItemDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemDomain> {
        return try {
            loadResults(params, _movieId)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private suspend fun loadResults(
        params: LoadParams<Int>,
        movieId: Long
    ): LoadResult.Page<Int, MovieListItemDomain> {
        val currentPage = params.key ?: 1
        val response = service.getMovieRecommendationsBy(movieId)

        return LoadResult.Page(
            data = response.results.filter { movieResponse ->
                movieResponse.title != null
                        && movieResponse.originalTitle != null
                        && movieResponse.posterPath != null
            }.map { mapper.map(it) },
            prevKey = null,
            nextKey = currentPage.takeIf { response.results.isNotEmpty() }?.inc()
        )
    }

}
