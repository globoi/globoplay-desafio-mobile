package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.FindMediaByIdUseCase

class FindMediaByIdUIMapper: CommonResultConverter<FindMediaByIdUseCase.Response, MediaDetails?>() {

    override fun convertSuccess(data: FindMediaByIdUseCase.Response): MediaDetails? {
        return data.mediaDetails
    }
}

