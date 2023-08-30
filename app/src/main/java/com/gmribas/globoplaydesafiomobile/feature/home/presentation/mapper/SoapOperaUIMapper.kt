package com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase.DiscoverSoapOperasUseCase

class SoapOperaUIMapper: CommonResultConverter<DiscoverSoapOperasUseCase.Response, PagingData<SoapOpera>>() {
    override fun convertSuccess(data: DiscoverSoapOperasUseCase.Response): PagingData<SoapOpera> {
        return data.data
    }
}