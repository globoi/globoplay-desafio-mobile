package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetTvShowDetailsUseCase

class TvShowDetailsUIMapper: CommonResultConverter<GetTvShowDetailsUseCase.Response, TvShowDetails>() {

    override fun convertSuccess(data: GetTvShowDetailsUseCase.Response): TvShowDetails {
        return data.details
    }
}

