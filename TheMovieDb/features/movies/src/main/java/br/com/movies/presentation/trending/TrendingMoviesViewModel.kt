package br.com.movies.presentation.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.movies.domain.usecase.trending.GetTrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TrendingUiState>(
        TrendingUiState.Success(PagingData.empty())
    )
    val uiState = _uiState.asStateFlow()

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            getTrendingMoviesUseCase().cachedIn(viewModelScope).collectLatest { pagingData ->
                _uiState.value = TrendingUiState.Success(pagingData)
            }
        }
    }
}