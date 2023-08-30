package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetTvShowDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MovieDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MoviePagedUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.TvShowDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.TvShowUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase,
    private val movieDetailsUIMapper: MovieDetailsUIMapper,
    private val movieUIMapper: MoviePagedUIMapper,
    private val tvShowUIMapper: TvShowUIMapper,
    private val tvShowDetailsUIMapper: TvShowDetailsUIMapper
    ): BaseViewModel<MovieDetails>() {

    // similar movies
    private val _similarMoviesFlow: MutableStateFlow<PagingData<Movie>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val similarMoviesFlow: StateFlow<PagingData<Movie>> = _similarMoviesFlow

    //similar tv shows
    private val _similarTvShowsFlow: MutableStateFlow<PagingData<TvShow>> by lazy {
        MutableStateFlow(PagingData.empty())
    }

    val similarTvShowsFlow: StateFlow<PagingData<TvShow>> = _similarTvShowsFlow

    //tv show details
    private val _tvShowsDetailsFlow: MutableStateFlow<UiState<TvShowDetails>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val tvShowsDetailsFlow: StateFlow<UiState<TvShowDetails>> = _tvShowsDetailsFlow.asStateFlow()

    // tab
    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val tabIndex: StateFlow<Int> = _tabIndex

    private val _isTvShow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isTvShow: StateFlow<Boolean> = _isTvShow

    override fun onCreate(owner: LifecycleOwner) {
        val id = savedStateHandle.get<Int>("id")
        val isTvShow = savedStateHandle.get<Boolean>("isTvShow") ?: false

        this._isTvShow.value = isTvShow

        id?.let {
            submitState(UiState.Loading)

            if (isTvShow) {
                getTvShowDetails(id)
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

    private fun getTvShowDetails(id: Int) {
        viewModelScope.launch {
            getTvShowDetailsUseCase
                .execute(GetTvShowDetailsUseCase.Request(id))
                .map { tvShowDetailsUIMapper.convert(it) }
                .collectLatest {
                    _tvShowsDetailsFlow.value = it
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