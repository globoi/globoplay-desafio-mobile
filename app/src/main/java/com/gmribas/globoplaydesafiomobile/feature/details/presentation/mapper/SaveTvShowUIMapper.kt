package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveTvShowUseCase

class SaveTvShowUIMapper: CommonResultConverter<SaveTvShowUseCase.Response, Long>() {

    override fun convertSuccess(data: SaveTvShowUseCase.Response): Long {
        return data.insert
    }
}

