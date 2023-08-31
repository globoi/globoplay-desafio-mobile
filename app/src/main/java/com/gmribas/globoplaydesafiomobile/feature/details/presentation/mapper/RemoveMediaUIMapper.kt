package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.RemoveMediaUseCase

class RemoveMediaUIMapper: CommonResultConverter<RemoveMediaUseCase.Response, Int>() {

    override fun convertSuccess(data: RemoveMediaUseCase.Response): Int {
        return data.removed
    }
}

