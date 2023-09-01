package com.tiagopereira.globotmdb.viewmodel.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tiagopereira.globotmdb.paging.MoviePagingSource
import com.tiagopereira.globotmdb.utils.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getFilterResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 1000,
                enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService, query)
            }
        ).liveData

}