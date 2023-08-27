package com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverMoviesUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.GetTopRatedTvShowsUseCase

class TopRatedTvShowUIMapper: CommonResultConverter<GetTopRatedTvShowsUseCase.Response, PagingData<SoapOpera>>() {
    override fun convertSuccess(data: GetTopRatedTvShowsUseCase.Response): PagingData<SoapOpera> {
        return data.data
    }
}