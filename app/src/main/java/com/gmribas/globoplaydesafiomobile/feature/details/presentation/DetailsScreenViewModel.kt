package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.DetailsInterface
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.core.presentation.BaseViewModel
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.FindMediaByIdUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetTvShowDetailsUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.RemoveMediaUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveMovieUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveTvShowUseCase
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.FindMediaByIdUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MovieDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MoviePagedUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.RemoveMediaUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.SaveMovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.SaveTvShowUIMapper
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
    private val removeMediaUseCase: RemoveMediaUseCase,
    private val saveTvShowUseCase: SaveTvShowUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val findMediaByIdUseCase: FindMediaByIdUseCase,
    private val movieDetailsUIMapper: MovieDetailsUIMapper,
    private val movieUIMapper: MoviePagedUIMapper,
    private val tvShowUIMapper: TvShowUIMapper,
    private val tvShowDetailsUIMapper: TvShowDetailsUIMapper,
    private val saveTvShowUIMapper: SaveTvShowUIMapper,
    private val saveMovieUIMapper: SaveMovieUIMapper,
    private val removeMediaUIMapper: RemoveMediaUIMapper,
    private val findMediaByIdUIMapper: FindMediaByIdUIMapper
) : BaseViewModel<MovieDetails>() {

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

    //save media
    private val _saveMediaFlow: MutableStateFlow<UiState<Long>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val saveMediaFlow: StateFlow<UiState<Long>> = _saveMediaFlow.asStateFlow()

    //remove media
    private val _removeMediaFlow: MutableStateFlow<UiState<Int>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val removeMediaFlow: StateFlow<UiState<Int>> = _removeMediaFlow.asStateFlow()

    //is media in my list
    private val _myListMedia: MutableStateFlow<UiState<MediaDetails?>> by lazy {
        MutableStateFlow(UiState.Default)
    }

    val myListMedia: StateFlow<UiState<MediaDetails?>> = _myListMedia.asStateFlow()

    // tab
    private val _tabIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    val tabIndex: StateFlow<Int> = _tabIndex

    private val _isTvShow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isTvShow: StateFlow<Boolean> = _isTvShow

    private var mediaId = -1

    override fun onCreate(owner: LifecycleOwner) {
        if (mediaId != -1) return // already loaded

        mediaId = savedStateHandle.get<Int>("id") ?: -1
        _isTvShow.value = savedStateHandle.get<Boolean>("isTvShow") ?: false

        if (mediaId != -1) {
            submitState(UiState.Loading)

            isTheMediaInMyList(mediaId)

            if (_isTvShow.value) {
                getTvShowDetails(mediaId)
                getSimilarTvShows(mediaId)
            } else {
                getMovieDetails(mediaId)
                getSimilarMovies(mediaId)
            }
        }
    }

    private fun updateFavoriteStatus() {
        if (mediaId > -1) {
            isTheMediaInMyList(mediaId)
        }
    }
    private fun isTheMediaInMyList(id: Int) {
        viewModelScope.launch {
            findMediaByIdUseCase
                .execute(FindMediaByIdUseCase.Request(id))
                .map { findMediaByIdUIMapper.convert(it) }
                .collectLatest {
                    _myListMedia.value = it
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
                .execute(GetSimilarMoviesUseCase.Request(viewModelScope, movieId))
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
                .execute(GetSimilarTvShowsUseCase.Request(viewModelScope, id))
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

    fun saveMedia(media: DetailsInterface) {
        if (media.isTvShow) {
            saveTvShow(media as TvShowDetails)
        } else {
            saveMovie(media as MovieDetails)
        }
    }

    private fun saveTvShow(tvShow: TvShowDetails) {
        viewModelScope.launch {
            saveTvShowUseCase
                .execute(SaveTvShowUseCase.Request(tvShow))
                .map { saveTvShowUIMapper.convert(it) }
                .collectLatest { uiState ->
                    _saveMediaFlow.value = uiState
                    updateFavoriteStatus()
                }
        }
    }

    private fun saveMovie(movie: MovieDetails) {
        viewModelScope.launch {
            saveMovieUseCase
                .execute(SaveMovieUseCase.Request(movie))
                .map { saveMovieUIMapper.convert(it) }
                .collectLatest { uiState ->
                    _saveMediaFlow.value = uiState
                    updateFavoriteStatus()
                }
        }
    }

    fun removeMedia(id: Int) {
        viewModelScope.launch {
            removeMediaUseCase
                .execute(RemoveMediaUseCase.Request(id))
                .map { removeMediaUIMapper.convert(it) }
                .collectLatest { uiState ->
                    _removeMediaFlow.value = uiState
                    updateFavoriteStatus()
                }
        }
    }
}