package br.com.movies.presentation

import androidx.paging.PagingData
import br.com.movies.domain.model.TrendingMovies

sealed class TrendingUiState {

    data class Success(
        val pagingData: PagingData<TrendingMovies>,
    ) : TrendingUiState()
}
