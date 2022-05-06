package com.ftoniolo.core.data.repository

import androidx.paging.PagingSource
import com.ftoniolo.core.domain.model.FilmsFromGenre

interface FilmsRepository {

    fun getFilms(query: String): PagingSource<Int, FilmsFromGenre>
}