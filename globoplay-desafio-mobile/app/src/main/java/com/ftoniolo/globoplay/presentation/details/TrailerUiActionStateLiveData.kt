package com.ftoniolo.globoplay.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ftoniolo.core.domain.model.Trailer
import com.ftoniolo.core.usecase.GetTrailerByIdUseCase
import com.ftoniolo.globoplay.presentation.extensions.watchStatus
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TrailerUiActionStateLiveData @Inject constructor(
    private val coroutineContext: CoroutineContext,
    private val getTrailerByIdUseCase: GetTrailerByIdUseCase
) {

    private val action = MutableLiveData<Action>()

    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.Load -> {
                    getTrailerByIdUseCase.invoke(
                        GetTrailerByIdUseCase.Params(it.filmId)
                    ).watchStatus(
                        loading = {},
                        success = { data ->
                            if(data.isNotEmpty()){
                                emit(UiState.Success(data))
                            } else {
                                emit(UiState.Error)
                            }
                        },
                        error = { emit(UiState.Error) },
                    )
                }
            }
        }
    }

    fun load(filmId: Long) {
        action.value = Action.Load(filmId)
    }

    sealed class UiState {
        data class Success(val trailerList: List<Trailer>) : UiState()
        object Error : UiState()
    }

    sealed class Action {
        data class Load(val filmId: Long) : Action()
    }
}