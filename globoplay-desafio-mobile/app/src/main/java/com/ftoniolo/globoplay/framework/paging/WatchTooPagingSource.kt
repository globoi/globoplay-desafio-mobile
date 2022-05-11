package com.ftoniolo.globoplay.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.WatchToo
import java.lang.Exception

class WatchTooPagingSource(
    private val remoteDataSource: FilmsRemoteDataSource,
    private val filmId: Long
) : PagingSource<Int, WatchToo>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WatchToo> {
        return try {
            val numberPag = params.key ?: 1

            val queries = hashMapOf(
                "pag" to numberPag.toString()
            )
            val response = remoteDataSource.fetchWatchToo(filmId, queries)

            val responsePage = response.page
            val responseTotalPage = response.totalPages

            LoadResult.Page(
                data = response.films,
                prevKey = null,
                nextKey = if (responsePage < responseTotalPage) {
                    numberPag + ONE
                } else null
            )

            LoadResult.Page(
                data = response.films,
                prevKey = null,
                nextKey = if (responsePage < responseTotalPage) {
                    numberPag + ONE
                } else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WatchToo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(ONE) ?: anchorPage?.nextKey?.minus(ONE)
        }
    }

    companion object {
        private const val ONE = 1
    }
}