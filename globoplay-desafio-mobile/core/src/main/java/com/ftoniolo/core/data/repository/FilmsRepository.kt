package com.ftoniolo.core.data.repository

import androidx.paging.PagingSource
import com.ftoniolo.core.domain.model.Film

interface FilmsRepository {

    fun getFilms(): PagingSource<Int, Film>
}