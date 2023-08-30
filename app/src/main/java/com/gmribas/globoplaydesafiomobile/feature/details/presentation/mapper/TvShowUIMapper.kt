package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow

class TvShowUIMapper: CommonResultConverter<GetSimilarTvShowsUseCase.Response, PagingData<TvShow>>() {
    override fun convertSuccess(data: GetSimilarTvShowsUseCase.Response): PagingData<TvShow> {
        return data.tvShows
    }
}