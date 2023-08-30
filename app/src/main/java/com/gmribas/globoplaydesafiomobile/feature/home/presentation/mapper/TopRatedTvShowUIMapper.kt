package com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.GetTopRatedTvShowsUseCase

class TopRatedTvShowUIMapper: CommonResultConverter<GetTopRatedTvShowsUseCase.Response, PagingData<TvShow>>() {
    override fun convertSuccess(data: GetTopRatedTvShowsUseCase.Response): PagingData<TvShow> {
        return data.data
    }
}