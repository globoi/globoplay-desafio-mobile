package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRemoteRepository

class GetMoviesByKeywordUseCase(
    private val repository: MovieRemoteRepository
) {
    operator fun invoke(keyword: String) = repository.getPagingMovieByKeyword(keyword)
}