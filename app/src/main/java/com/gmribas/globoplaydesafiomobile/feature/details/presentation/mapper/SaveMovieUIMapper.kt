package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.SaveMovieUseCase

class SaveMovieUIMapper: CommonResultConverter<SaveMovieUseCase.Response, Long>() {

    override fun convertSuccess(data: SaveMovieUseCase.Response): Long {
        return data.insert
    }
}

