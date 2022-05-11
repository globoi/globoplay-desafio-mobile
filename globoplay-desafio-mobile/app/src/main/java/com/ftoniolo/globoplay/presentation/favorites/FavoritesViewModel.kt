package com.ftoniolo.globoplay.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ftoniolo.core.usecase.GetFavoritesUseCase
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap { action ->
        liveData(coroutinesDispatchers.main()) {
            when (action) {
                is Action.GetAll -> {
                    getFavoritesUseCase.invoke()
                        .collect {
                            val items = it.map { film ->
                                FilmItem(
                                    film.id,
                                    film.title,
                                    film.imageUrl
                                )
                            }
                            val uiState = if (items.isEmpty()) {
                                UiState.ShowEmpty
                            } else UiState.ShowFavorite(items)
                            emit(uiState)
                        }
                }
            }
        }
    }

    fun getAll() {
        action.value = Action.GetAll
    }


    sealed class UiState {
        data class ShowFavorite(val favorites: List<FilmItem>) : UiState()
        object ShowEmpty : UiState()
    }

    sealed class Action {
        object GetAll : Action()
    }
}