package com.gmribas.globoplaydesafiomobile.feature.mylist.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.usecase.GetAllSavedMediaUseCase

class MediaListUIMapper: CommonResultConverter<GetAllSavedMediaUseCase.Response, List<Media>>() {

    override fun convertSuccess(data: GetAllSavedMediaUseCase.Response): List<Media> {
        return data.medias
    }
}