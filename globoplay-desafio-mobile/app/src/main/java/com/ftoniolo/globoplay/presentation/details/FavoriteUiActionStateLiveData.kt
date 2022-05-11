package com.ftoniolo.globoplay.presentation.details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ftoniolo.core.usecase.AddFavoriteUseCase
import com.ftoniolo.core.usecase.CheckFavoriteUseCase
import com.ftoniolo.core.usecase.RemoveFavoriteUseCase
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.presentation.extensions.watchStatus
import kotlin.coroutines.CoroutineContext

class FavoriteUiActionStateLiveData(
    private val coroutineContext: CoroutineContext,
    private val checkFavoriteUseCase: CheckFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase)
{

    private var currentFavoriteButton = UiState.Button(
        R.drawable.ic_favorite_star_menu,
        FAVORITE_NOT
    )

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.CheckFavorite -> {
                    checkFavoriteUseCase.invoke(
                        CheckFavoriteUseCase.Params(it.filmId)
                    ).watchStatus(
                        success = { isFavorite ->
                            if (isFavorite) {
                                currentFavoriteButton = UiState.Button(
                                    R.drawable.ic_favorite_check,
                                    FAVORITE_ADD
                                )
                            }
                            emitFavoriteButton()
                        },
                        error = {}
                    )
                }
                is Action.AddFavorite -> {
                    it.detailsFilmViewArg.run {
                        addFavoriteUseCase.invoke(
                            AddFavoriteUseCase.Params(
                                id, title, imageUrl
                            )
                        ).watchStatus(
                            loading = {
                                emit(UiState.Loading)
                            },
                            success = {
                                currentFavoriteButton = UiState.Button(
                                    R.drawable.ic_favorite_check,
                                    FAVORITE_ADD
                                )
                                emitFavoriteButton()
                            },
                            error = {
                                emit(UiState.Error(R.string.error_add_favorite))
                            }
                        )
                    }
                }
                is Action.RemoveFavorite -> {
                    it.detailsFilmViewArg.run {
                        removeFavoriteUseCase.invoke(
                            RemoveFavoriteUseCase.Params(id, title, imageUrl)
                        ).watchStatus(
                            loading = {
                                emit(UiState.Loading)
                            },
                            success = {
                                currentFavoriteButton = UiState.Button(
                                    R.drawable.ic_favorite_star_menu,
                                    FAVORITE_NOT
                                )
                                emitFavoriteButton()
                            },
                            error = {
                                emit(UiState.Error(R.string.error_remove_favorite))
                            }
                        )
                    }
                }
            }
        }
    }

    private suspend fun LiveDataScope<UiState>.emitFavoriteButton(){
        emit(currentFavoriteButton)
    }

    fun checkFavorite(filmId: Long) {
        action.value = Action.CheckFavorite(filmId)
    }

    fun update(detailsFilmViewArg: DetailsFilmViewArg) {
        action.value = if(currentFavoriteButton.icon == R.drawable.ic_favorite_star_menu) {
            Action.AddFavorite(detailsFilmViewArg)
        } else Action.RemoveFavorite(detailsFilmViewArg)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Button(@DrawableRes val icon: Int, val buttonDescription: String) :
            UiState()

        data class Error(@StringRes val messageResId: Int) : UiState()
    }

    sealed class Action {
        data class CheckFavorite(val filmId: Long) : Action()
        data class AddFavorite(val detailsFilmViewArg: DetailsFilmViewArg) : Action()
        data class RemoveFavorite(val detailsFilmViewArg: DetailsFilmViewArg) : Action()
    }

    companion object {
        const val FAVORITE_ADD = "Adicionado"
        const val FAVORITE_NOT = "Minha Lista"
    }
}


