package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val useCase: DiscoverMoviesUseCase,
    private val mapper: MovieUIMapper
): BaseViewModel<PagingData<Movie>>() {

    private val _discoverMoviesState: MutableStateFlow<UiState<PagingData<Movie>>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    private val _discoverMoviesFlow: MutableStateFlow<PagingData<Movie>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val discoverMoviesFlow = _discoverMoviesFlow
    override fun onResume(owner: LifecycleOwner) {
        discoverMovies()
    }
    private fun discoverMovies() {
        submitState(UiState.Loading)

        viewModelScope.launch {
            useCase
                .execute(DiscoverMoviesUseCase.Request(viewModelScope))
                .map { mapper.convert(it) }
                .collectLatest { state ->
                    _discoverMoviesState.value = state

                    if (state is UiState.Success) {
                        _discoverMoviesFlow.value = state.data
                    }
                }
        }
    }

    fun dismissErrorDialog() {
        submitState(UiState.Default)
    }
}