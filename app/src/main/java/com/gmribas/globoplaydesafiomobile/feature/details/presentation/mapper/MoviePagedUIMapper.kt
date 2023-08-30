package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarMoviesUseCase

class MoviePagedUIMapper: CommonResultConverter<GetSimilarMoviesUseCase.Response, PagingData<Movie>>() {
    override fun convertSuccess(data: GetSimilarMoviesUseCase.Response): PagingData<Movie> {
        return data.movies
    }
}