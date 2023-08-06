package com.reisdeveloper.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reisdeveloper.data.dataModel.Movie
import com.reisdeveloper.data.repository.MovieRepository
import com.reisdeveloper.domain.MovieListType
import com.reisdeveloper.domain.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    fun execute(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(movieRepository, MovieListType.POPULAR) }
        ).flow
    }

    companion object {
        const val ITEMS_PER_PAGE = 21
    }
}