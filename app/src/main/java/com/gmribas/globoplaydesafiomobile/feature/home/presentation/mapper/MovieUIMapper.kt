package com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase

class MovieUIMapper: CommonResultConverter<DiscoverMoviesUseCase.Response, PagingData<Movie>>() {
    override fun convertSuccess(data: DiscoverMoviesUseCase.Response): PagingData<Movie> {
        return data.data
    }
}