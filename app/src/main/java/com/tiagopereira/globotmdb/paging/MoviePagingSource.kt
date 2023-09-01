package com.tiagopereira.globotmdb.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tiagopereira.globotmdb.data.ApiResponse
import com.tiagopereira.globotmdb.utils.Constants.Companion.KEY_API
import com.tiagopereira.globotmdb.utils.Constants.Companion.LANGUAGE
import com.tiagopereira.globotmdb.utils.Constants.Companion.NOW_PLAYING
import com.tiagopereira.globotmdb.utils.Constants.Companion.POPULAR
import com.tiagopereira.globotmdb.utils.Constants.Companion.TOP_RATED
import com.tiagopereira.globotmdb.utils.Constants.Companion.UPCOMING
import com.tiagopereira.globotmdb.utils.RetrofitService
import retrofit2.HttpException
import retrofit2.Response

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class MoviePagingSource (private val retrofitService: RetrofitService, private val query: String) : PagingSource<Int, ApiResponse.Result>() {

    override fun getRefreshKey(state: PagingState<Int, ApiResponse.Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponse.Result> {
        return try {
            val currentPage = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val response = callTypeMovies(query, currentPage.toString())
            val data = response.body()!!.results
            val responseData = mutableListOf<ApiResponse.Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == UNSPLASH_STARTING_PAGE_INDEX) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun callTypeMovies(query: String, currentPage: String): Response<ApiResponse> {
        return when(query) {
            POPULAR -> retrofitService.getPopularMovies(KEY_API, LANGUAGE, currentPage)
            TOP_RATED -> retrofitService.getTopRatedMovies(KEY_API, LANGUAGE, currentPage)
            UPCOMING -> retrofitService.getUpcomingMovies(KEY_API, LANGUAGE, currentPage)
            NOW_PLAYING -> retrofitService.getNowPlayingMovies(KEY_API, LANGUAGE, currentPage)
            else -> retrofitService.getSearchByName(query, false, KEY_API, LANGUAGE, currentPage)
        }
    }
}