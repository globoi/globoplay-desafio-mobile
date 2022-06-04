package com.simonassi.globoplay.data.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.simonassi.globoplay.api.TMDBService
import java.lang.Exception
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val service: TMDBService):
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage: Int = params.key?: FIRST_PAGE_INDEX
            val response = service.discoverMovies(page = nextPage)
            var nextPageNumber: Int? = null
            if(response.page +1 < response.totalPages ){
                nextPageNumber = response.page+1
            }

            LoadResult.Page(data = response.results, nextKey = nextPageNumber, prevKey = null)
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}