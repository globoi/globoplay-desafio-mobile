package com.reisdeveloper.globoplay.ui.features.movie.watchtoo

import com.reisdeveloper.domain.usecases.GetSimilarMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class WatchViewModel(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getSimilarMovies(movieId: String) {
        getSimilarMoviesUseCase(movieId).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = { favoriteMovies ->
                _screen.emit(
                    Screen.WatchToo(
                        favoriteMovies.results.map {
                            it.toUiModel()
                        }
                    )
                )
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class WatchToo(val movies: List<MovieUiModel>) : Screen()
        object Error : Screen()
    }

}