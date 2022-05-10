package com.ftoniolo.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ftoniolo.core.domain.model.WatchToo

class PagingSourceFactory {

    fun create(movies: List<WatchToo>) = object : PagingSource<Int, WatchToo>(){
        override fun getRefreshKey(state: PagingState<Int, WatchToo>): Int? = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WatchToo> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = 1
            )
        }
    }
}