package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MovieDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MoviePagedUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.TvShowUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    private val movieDetailsUIMapper: MovieDetailsUIMapper,
    private val movieUIMapper: MoviePagedUIMapper,
    private val tvShowUIMapper: TvShowUIMapper,
    ): BaseViewModel<MovieDetails>() {

    private val _similarMoviesFlow: MutableStateFlow<PagingData<Movie>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val similarMoviesFlow: StateFlow<PagingData<Movie>> = _similarMoviesFlow

    private val _similarTvShowsFlow: MutableStateFlow<PagingData<TvShow>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val similarTvShowsFlow: StateFlow<PagingData<TvShow>> = _similarTvShowsFlow

    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val tabIndex: StateFlow<Int> = _tabIndex

    override fun onCreate(owner: LifecycleOwner) {
        submitState(UiState.Loading)

        val id = savedStateHandle.get<Int>("id")
        val isTvShow = savedStateHandle.get<Boolean>("isTvShow")

        if (id != null && isTvShow != null) {
            if (isTvShow) {
                getSimilarTvShows(id)
            } else {
                getMovieDetails(id)
                getSimilarMovies(id)
            }
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase
                .execute(GetMovieDetailsUseCase.Request(movieId))
                .map { movieDetailsUIMapper.convert(it) }
                .collectLatest {
                    submitState(it)
                }
        }
    }

    private fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            getSimilarMoviesUseCase
                .execute(GetSimilarMoviesUseCase.Request(movieId))
                .map { movieUIMapper.convert(it) }
                .collectLatest { uiState ->
                    if (uiState is UiState.Success) {
                        _similarMoviesFlow.value = uiState.data
                    }
                }
        }
    }

    private fun getSimilarTvShows(id: Int) {
        viewModelScope.launch {
            getSimilarTvShowsUseCase
                .execute(GetSimilarTvShowsUseCase.Request(id))
                .map { tvShowUIMapper.convert(it) }
                .collectLatest { uiState ->
                    if (uiState is UiState.Success) {
                        _similarTvShowsFlow.value = uiState.data
                    }
                }
        }
    }

    fun updateTabIndex(index: Int) {
        _tabIndex.value = index
    }
}