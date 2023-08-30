package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.GetTopRatedTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.SoapOperaUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.TopRatedTvShowUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,
    private val discoverSoapOperasUseCase: DiscoverSoapOperasUseCase,
    private val topRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    private val movieUIMapper: MovieUIMapper,
    private val soapOperaUIMapper: SoapOperaUIMapper,
    private val topRatedTvShowsUIMapper: TopRatedTvShowUIMapper,
): BaseViewModel<PagingData<Movie>>() {

    // Movies
    private val _discoverMoviesFlow: MutableStateFlow<PagingData<Movie>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val discoverMoviesFlow = _discoverMoviesFlow

    //Soap operas
    private val _discoverSoapOperasFlow: MutableStateFlow<PagingData<SoapOpera>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val discoverSoapOperasFlow = _discoverSoapOperasFlow

    //Top rated
    private val _topRatedTvShowsFlow: MutableStateFlow<PagingData<SoapOpera>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val topRatedTvShowsFlow = _topRatedTvShowsFlow

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getTopRatedTvShows()
        discoverSoapOperas()
        discoverMovies()
    }

    private fun discoverMovies() {
        viewModelScope.launch {
            discoverMoviesUseCase
                .execute(DiscoverMoviesUseCase.Request(viewModelScope))
                .map { movieUIMapper.convert(it) }
                .collectLatest { state ->
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
                    if (state is UiState.Success) {
                        _discoverSoapOperasFlow.value = state.data
                    }
                }
        }
    }

    private fun getTopRatedTvShows() {
        viewModelScope.launch {
            topRatedTvShowsUseCase
                .execute(GetTopRatedTvShowsUseCase.Request(viewModelScope))
                .map { topRatedTvShowsUIMapper.convert(it) }
                .collectLatest { state ->
                    if (state is UiState.Success) {
                        _topRatedTvShowsFlow.value = state.data
                    }
                }
        }
    }
}