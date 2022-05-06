package com.ftoniolo.globoplay.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.framework.network.response.film.FilmsDataWrapperResponse
import com.ftoniolo.globoplay.framework.network.response.film.toFilmModel
import java.lang.Exception

class FilmsPagingSource(
    private val remoteDataSource: FilmsRemoteDataSource<FilmsDataWrapperResponse>
) : PagingSource<Int, Film>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val numberPag = params.key ?: 1

            val queries = hashMapOf(
                "pag" to numberPag.toString()
            )

            val response = remoteDataSource.fetchFilms(queries)

            val responsePage = response.page
            val responseTotalPage = response.totalPages

            LoadResult.Page(
                data = response.results.map { it.toFilmModel()},
                prevKey = null,
                nextKey = if(responsePage < responseTotalPage){
                    numberPag + ONE
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(ONE) ?: anchorPage?.nextKey?.minus(ONE)
        }
    }

    companion object {
        private const val ONE = 1
    }
}