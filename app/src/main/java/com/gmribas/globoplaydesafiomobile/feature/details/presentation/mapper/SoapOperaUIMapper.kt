package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetSimilarTvShowsUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera

class SoapOperaUIMapper: CommonResultConverter<GetSimilarTvShowsUseCase.Response, PagingData<SoapOpera>>() {
    override fun convertSuccess(data: GetSimilarTvShowsUseCase.Response): PagingData<SoapOpera> {
        return data.tvShows
    }
}