package com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverTvShowsUseCase

class TvShowUIMapper: CommonResultConverter<DiscoverTvShowsUseCase.Response, PagingData<TvShow>>() {
    override fun convertSuccess(data: DiscoverTvShowsUseCase.Response): PagingData<TvShow> {
        return data.data
    }
}