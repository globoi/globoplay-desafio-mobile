package com.reisdeveloper.globoplay.ui.features.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.reisdeveloper.domain.usecases.GetNowPlayingMoviesUseCase
import com.reisdeveloper.domain.usecases.GetPopularMoviesUseCase
import com.reisdeveloper.domain.usecases.GetTopRatedMoviesUseCase
import com.reisdeveloper.domain.usecases.GetUpcomingMoviesUseCase
import com.reisdeveloper.domain.usecases.SearchMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map

class HomeViewModel(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

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

    fun searchMovies(query: String) {
        searchMoviesUseCase(query).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = { movies ->
                _screen.emit(Screen.SearchedMovies(movies.results.map {
                    it.toUiModel()
                }))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class SearchedMovies(val movies: List<MovieUiModel>) : Screen()
        object Error : Screen()
    }
}