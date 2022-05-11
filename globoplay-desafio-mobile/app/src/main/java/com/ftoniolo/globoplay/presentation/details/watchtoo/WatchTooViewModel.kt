package com.ftoniolo.globoplay.presentation.details.watchtoo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.core.usecase.GetWatchTooUseCase
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WatchTooViewModel @Inject constructor(
    private val getWatchTooUseCase: GetWatchTooUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {


    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action
        .distinctUntilChanged()
        .switchMap {action ->
            when (action) {
                is Action.Search -> {
                    getWatchTooUseCase(
                        GetWatchTooUseCase.GetWatchTooParams(action.filmId, getPageConfig())
                    ).cachedIn(viewModelScope).map {
                        UiState.SearchResult(it)
                    }.asLiveData(coroutinesDispatchers.main())
                }
            }
        }

    fun watchTooPagingData(filmId: Long): Flow<PagingData<WatchToo>> {
        return getWatchTooUseCase(
            GetWatchTooUseCase.GetWatchTooParams(filmId, getPageConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )

    fun showFilms(filmId: Long) {
        action.value = Action.Search(filmId)
    }

    sealed class UiState {
        data class SearchResult(val data: PagingData<WatchToo>) : UiState()
    }

    sealed class Action {
        data class Search(val filmId: Long) : Action()
    }
}