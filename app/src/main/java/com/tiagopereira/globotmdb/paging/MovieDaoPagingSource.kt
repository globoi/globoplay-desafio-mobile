package com.tiagopereira.globotmdb.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tiagopereira.globotmdb.database.dao.MovieDao
import com.tiagopereira.globotmdb.database.entities.Movie
import retrofit2.HttpException

private const val UNSPLASH_STARTING_PAGE_INDEX = 0
class MovieDaoPagingSource (private val movieDao: MovieDao) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val max = currentPage + 20
            val response = movieDao.getMoviePagination(currentPage, max)
            val responseData = mutableListOf<Movie>()
            responseData.addAll(response)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == UNSPLASH_STARTING_PAGE_INDEX) null else -1,
                nextKey = if (response.isNotEmpty()) currentPage.plus(20) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}