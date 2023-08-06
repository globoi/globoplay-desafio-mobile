package com.reisdeveloper.globoplay.ui.features.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reisdeveloper.domain.usecases.GetNowPlayingMoviesUseCase
import com.reisdeveloper.domain.usecases.GetPopularMoviesUseCase
import com.reisdeveloper.domain.usecases.GetTopRatedMoviesUseCase
import com.reisdeveloper.domain.usecases.GetUpcomingMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel

class HomeViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : BaseViewModel() {

    fun getNowPlayingMovies() =
        getNowPlayingMoviesUseCase.execute().cachedIn(viewModelScope)

    fun getPopularMovies() =
        getPopularMoviesUseCase.execute().cachedIn(viewModelScope)

    fun getTopRatedMovies() =
        getTopRatedMoviesUseCase.execute().cachedIn(viewModelScope)

    fun getUpcomingMovies() =
        getUpcomingMoviesUseCase.execute().cachedIn(viewModelScope)

}