package com.reisdeveloper.globoplay.ui.features.home

import com.reisdeveloper.domain.MovieListType
import com.reisdeveloper.domain.usecases.GetMovieListUseCase
import com.reisdeveloper.domain.usecases.SearchMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class HomeViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getMovieListUseCase: GetMovieListUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getNowPlayingMovies() {
        getMovieList(MovieListType.NOW_PLAYING)
    }

    fun getPopularMovies() {
        getMovieList(MovieListType.POPULAR)
    }

    fun getTopRatedMovies() {
        getMovieList(MovieListType.TOP_RATED)
    }

    fun getUpcomingMovies() {
        getMovieList(MovieListType.UPCOMING)
    }

    private fun getMovieList(movieListType: MovieListType) {
        getMovieListUseCase(movieListType).singleExec(
            onLoadingBaseViewModel = {
                when(movieListType) {
                    MovieListType.NOW_PLAYING -> _screen.emit(Screen.LoadingNowPlaying(it))

                    MovieListType.POPULAR -> _screen.emit(Screen.LoadingPopular(it))

                    MovieListType.TOP_RATED -> _screen.emit(Screen.LoadingTopRated(it))

                    MovieListType.UPCOMING -> _screen.emit(Screen.LoadingUpcoming(it))
                }
            },
            onError = {

            },
            onSuccessBaseViewModel = {movies ->
                val movieList = movies.results.map {
                    it.toUiModel()
                }

                when(movieListType) {
                    MovieListType.NOW_PLAYING -> _screen.emit(Screen.NowPlayingList(movieList))

                    MovieListType.POPULAR -> _screen.emit(Screen.PopularList(movieList))

                    MovieListType.TOP_RATED -> _screen.emit(Screen.TopRatedList(movieList))

                    MovieListType.UPCOMING -> _screen.emit(Screen.UpcomingList(movieList))
                }
            }
        )
    }

    fun searchMovies(query: String) {
        searchMoviesUseCase(query).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.LoadingSearch(it))
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
        data class LoadingNowPlaying(val loading: Boolean) : Screen()
        data class LoadingPopular(val loading: Boolean) : Screen()
        data class LoadingTopRated(val loading: Boolean) : Screen()
        data class LoadingUpcoming(val loading: Boolean) : Screen()
        data class LoadingSearch(val loading: Boolean) : Screen()
        data class NowPlayingList(val movies: List<MovieUiModel>) : Screen()
        data class PopularList(val movies: List<MovieUiModel>) : Screen()
        data class TopRatedList(val movies: List<MovieUiModel>) : Screen()
        data class UpcomingList(val movies: List<MovieUiModel>) : Screen()
        data class SearchedMovies(val movies: List<MovieUiModel>) : Screen()
        object Error : Screen()
    }
}