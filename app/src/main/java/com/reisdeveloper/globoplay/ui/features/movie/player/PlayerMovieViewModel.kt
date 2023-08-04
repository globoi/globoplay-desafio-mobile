package com.reisdeveloper.globoplay.ui.features.movie.player

import com.reisdeveloper.data.dataModel.MovieVideos
import com.reisdeveloper.domain.usecases.GetMovieVideosUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class PlayerMovieViewModel(
    private val getMovieVideosUseCase: GetMovieVideosUseCase
) : BaseViewModel() {

    private val _screen = MutableSharedFlow<Screen>()
    val screen: SharedFlow<Screen> = _screen

    fun getMovieVideos(movieId: String) {
        getMovieVideosUseCase(movieId).singleExec(
            onError = {
                _screen.emit(Screen.Error)
            },
            onSuccessBaseViewModel = {
                _screen.emit(Screen.GetMovieVideos(it))
            }
        )
    }

    sealed class Screen {
        object Error : Screen()
        data class GetMovieVideos(val movies: MovieVideos) : Screen()
    }
}