package com.reisdeveloper.globoplay.ui.features.movie.details

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.domain.usecases.FavoriteMovieUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MovieDetailsViewModel(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun favoriteMovie(favorite: Favorite) {
        favoriteMovieUseCase(favorite).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = {
                _screen.emit(Screen.FavoriteMovie(favorite.favorite))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class FavoriteMovie(val favorite: Boolean) : Screen()
        object Error : Screen()
    }
}