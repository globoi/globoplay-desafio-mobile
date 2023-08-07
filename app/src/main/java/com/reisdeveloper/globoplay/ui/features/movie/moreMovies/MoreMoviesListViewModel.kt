package com.reisdeveloper.globoplay.ui.features.movie.moreMovies

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.reisdeveloper.domain.MovieListType
import com.reisdeveloper.domain.usecases.GetMovieListPagingUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import kotlinx.coroutines.flow.map

class MoreMoviesListViewModel(
    private val getMovieListPagingUseCase: GetMovieListPagingUseCase
) : BaseViewModel() {

    fun getMoreMoviesByType(movieListType: MovieListType) =
        getMovieListPagingUseCase.execute(movieListType).map { paging ->
            paging.map { it.toUiModel() }
        }.cachedIn(viewModelScope)

}