package com.reisdeveloper.globoplay.ui.features.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.reisdeveloper.domain.usecases.GetNowPlayingMoviesUseCase
import com.reisdeveloper.domain.usecases.GetPopularMoviesUseCase
import com.reisdeveloper.domain.usecases.GetTopRatedMoviesUseCase
import com.reisdeveloper.domain.usecases.GetUpcomingMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : BaseViewModel() {

    fun getNowPlayingMovies() =
        getNowPlayingMoviesUseCase.execute().map { paging ->
            paging.map { it.toUiModel() }
        }.cachedIn(viewModelScope)

    fun getPopularMovies() =
        getPopularMoviesUseCase.execute().map { paging ->
            paging.map { it.toUiModel() }
        }.cachedIn(viewModelScope)

    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.execute().map { paging ->
            paging.map { it.toUiModel() }
        }.cachedIn(viewModelScope)

    fun getUpcomingMovies() =
        getUpcomingMoviesUseCase.execute().map { paging ->
            paging.map { it.toUiModel() }
        }.cachedIn(viewModelScope)

}