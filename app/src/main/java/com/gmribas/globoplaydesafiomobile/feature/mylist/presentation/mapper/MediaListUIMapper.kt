package com.gmribas.globoplaydesafiomobile.feature.mylist.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.usecase.GetAllSavedMediaUseCase

class MediaListUIMapper: CommonResultConverter<GetAllSavedMediaUseCase.Response, List<MediaDetails>>() {

    override fun convertSuccess(data: GetAllSavedMediaUseCase.Response): List<MediaDetails> {
        return data.mediaDetails
    }
}