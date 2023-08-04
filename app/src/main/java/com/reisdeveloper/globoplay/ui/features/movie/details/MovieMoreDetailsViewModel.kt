package com.reisdeveloper.globoplay.ui.features.movie.details

import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.domain.usecases.GetMovieDetailsUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MovieMoreDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getMoreDetails(movieId: String) {
        getMovieDetailsUseCase(movieId).singleExec(
            onLoadingBaseViewModel = {
                _screen.emit(Screen.Loading(it))
            },
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = {
                _screen.emit(Screen.MovieMoreDetails(it))
            }
        )
    }

    sealed class Screen {
        data class Loading(val loading: Boolean) : Screen()
        data class MovieMoreDetails(val movieDetails: MovieDetails) : Screen()
        object Error : Screen()
    }
}