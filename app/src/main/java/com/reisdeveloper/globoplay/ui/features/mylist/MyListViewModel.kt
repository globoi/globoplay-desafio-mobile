package com.reisdeveloper.globoplay.ui.features.mylist

import com.reisdeveloper.domain.usecases.GetFavoriteMoviesUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import com.reisdeveloper.globoplay.mapper.toUiModel
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MyListViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getUserList(accountId: String) {
        getFavoriteMoviesUseCase(accountId).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = { favoriteMovies ->
                _screen.emit(Screen.FavoriteMovies(favoriteMovies.results.map {
                    it.toUiModel()
                }))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class FavoriteMovies(val movies: List<MovieUiModel>) : Screen()
        object Error : Screen()
    }
}