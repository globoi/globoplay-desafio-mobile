package com.ftoniolo.core.data.repository

import androidx.paging.PagingSource
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.WatchToo

interface FilmsRepository {

    fun getFilms(): PagingSource<Int, Film>

    fun getWatchToo(filmId: Long): PagingSource<Int, WatchToo>

    //    suspend fun getWatchToo(filmId: Long): List<WatchToo>
}