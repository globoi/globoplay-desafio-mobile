package com.ftoniolo.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.testing.model.FilmsFactory

class PagingSourceFactory {

    fun create(movies: List<Film>) = object : PagingSource<Int, Film>(){
        override fun getRefreshKey(state: PagingState<Int, Film>): Int? = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = 1
            )
        }
    }
}