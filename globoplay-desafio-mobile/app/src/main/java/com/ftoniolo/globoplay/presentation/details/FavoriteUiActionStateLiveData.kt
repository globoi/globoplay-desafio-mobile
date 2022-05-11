package com.ftoniolo.globoplay.presentation.details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ftoniolo.core.usecase.AddFavoriteUseCase
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.presentation.extensions.watchStatus
import kotlin.coroutines.CoroutineContext

class FavoriteUiActionStateLiveData(
    private val coroutineContext: CoroutineContext,
    private val addFavoriteUseCase: AddFavoriteUseCase
) {

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                Action.Default -> emit(
                    UiState.Button(
                        R.drawable.ic_favorite_star_menu,
                        FAVORITE_NOT
                    )
                )
                is Action.Update -> {
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
                                emit(UiState.Button(
                                        R.drawable.ic_favorite_check,
                                        FAVORITE_ADD
                                    ))
                            },
                            error = {
                                emit(UiState.Error(R.string.error_add_favorite))
                            }
                        )
                    }
                }
            }
        }
    }

    fun setDefault() {
        action.value = Action.Default
    }

    fun update(detailsFilmViewArg: DetailsFilmViewArg) {
        action.value = Action.Update(detailsFilmViewArg)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Button(@DrawableRes val icon: Int, val buttonDescription: String) :
            UiState()

        data class Error(@StringRes val messageResId: Int) : UiState()
    }

    sealed class Action {
        object Default : Action()
        data class Update(val detailsFilmViewArg: DetailsFilmViewArg) : Action()
    }

    companion object {
        const val FAVORITE_ADD = "Adicionado"
        const val FAVORITE_NOT = "Minha Lista"
    }
}


