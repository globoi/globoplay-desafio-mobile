package com.ftoniolo.globoplay.presentation.details

import androidx.lifecycle.ViewModel
import com.ftoniolo.core.usecase.AddFavoriteUseCase
import com.ftoniolo.core.usecase.CheckFavoriteUseCase
import com.ftoniolo.core.usecase.GetTrailerByIdUseCase
import com.ftoniolo.core.usecase.RemoveFavoriteUseCase
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFilmViewModel @Inject constructor(
    checkFavoriteUseCase: CheckFavoriteUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    removeFavoriteUseCase: RemoveFavoriteUseCase,
    trailerByIdUseCase: GetTrailerByIdUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        checkFavoriteUseCase,
        addFavoriteUseCase,
        removeFavoriteUseCase
    )

    val trailer = TrailerUiActionStateLiveData(
        coroutinesDispatchers.io(),
        trailerByIdUseCase
    )
}