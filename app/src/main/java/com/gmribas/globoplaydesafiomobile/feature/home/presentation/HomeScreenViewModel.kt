package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.SoapOperaUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val discoverSoapOperasUseCase: DiscoverSoapOperasUseCase,
    private val movieUIMapper: MovieUIMapper,
    private val soapOperaUIMapper: SoapOperaUIMapper,
): BaseViewModel<PagingData<Movie>>() {

    private val _discoverMoviesState: MutableStateFlow<UiState<PagingData<Movie>>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    private val _discoverMoviesFlow: MutableStateFlow<PagingData<Movie>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val discoverMoviesFlow = _discoverMoviesFlow


    private val _discoverSoapOperasState: MutableStateFlow<UiState<PagingData<SoapOpera>>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    private val _discoverSoapOperasFlow: MutableStateFlow<PagingData<SoapOpera>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val discoverSoapOperasFlow = _discoverSoapOperasFlow


    override fun onResume(owner: LifecycleOwner) {
        discoverMovies()
        discoverSoapOperas()
    }
    private fun discoverMovies() {
        viewModelScope.launch {
            discoverMoviesUseCase
                .execute(DiscoverMoviesUseCase.Request(viewModelScope))
                .map { movieUIMapper.convert(it) }
                .collectLatest { state ->
                    _discoverMoviesState.value = state

                    if (state is UiState.Success) {
                        _discoverMoviesFlow.value = state.data
                    }
                }
        }
    }

    private fun discoverSoapOperas() {
        viewModelScope.launch {
            discoverSoapOperasUseCase
                .execute(DiscoverSoapOperasUseCase.Request(viewModelScope))
                .map { soapOperaUIMapper.convert(it) }
                .collectLatest { state ->
                    _discoverSoapOperasState.value = state

                    if (state is UiState.Success) {
                        _discoverSoapOperasFlow.value = state.data
                    }
                }
        }
    }

    fun dismissErrorDialog() {
        submitState(UiState.Default)
    }
}