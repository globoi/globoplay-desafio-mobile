package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.presentation.CommonResultConverter
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetMovieDetailsUseCase
class MovieDetailsUIMapper: CommonResultConverter<GetMovieDetailsUseCase.Response, MovieDetails>() {

    override fun convertSuccess(data: GetMovieDetailsUseCase.Response): MovieDetails {
        return data.movieDetails
    }
}

