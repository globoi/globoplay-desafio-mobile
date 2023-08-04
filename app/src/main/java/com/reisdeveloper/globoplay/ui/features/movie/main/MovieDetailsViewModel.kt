package com.reisdeveloper.globoplay.ui.features.movie.main

import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.domain.usecases.FavoriteMovieUseCase
import com.reisdeveloper.globoplay.base.BaseViewModel

class MovieDetailsViewModel(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : BaseViewModel() {

    fun favoriteMovie(accountId: String, favorite: Favorite) {
        favoriteMovieUseCase(Pair(accountId, favorite)).singleExec(
            onError = {

            },
            onSuccessBaseViewModel = {

            }
        )
    }
}