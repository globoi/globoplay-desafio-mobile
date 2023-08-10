package br.com.movies.presentation.trending

import androidx.paging.PagingData
import br.com.common.domain.model.Movie

sealed class TrendingUiState {

    data class Success(
        val pagingData: PagingData<Movie>,
    ) : TrendingUiState()
}
