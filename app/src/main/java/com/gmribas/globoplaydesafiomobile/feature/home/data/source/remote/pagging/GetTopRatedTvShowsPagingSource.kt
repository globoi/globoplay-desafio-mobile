package com.gmribas.globoplaydesafiomobile.feature.home.data.source.remote.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.DECREMENT_VALUE
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.INCREMENT_VALUE
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.PAGE_INITIAL_VALUE
import com.gmribas.globoplaydesafiomobile.core.data.network.ApiService
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import retrofit2.HttpException
import java.io.IOException

class GetTopRatedTvShowsPagingSource(
    private val apiService: ApiService,
): PagingSource<Int, TvShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val pageIndex = params.key ?: PAGE_INITIAL_VALUE

        return try {
            val response = apiService.getTopRatedTvShows(page = pageIndex)

            val responseMapped = response.results.map { it.toDomain() }

            val nextKey = if (response.results.isEmpty()) null else pageIndex + 1

            LoadResult.Page(
                data = responseMapped,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(INCREMENT_VALUE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(DECREMENT_VALUE)
        }
    }
}