package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.repository.MovieRepository

class GetMoviesByKeywordUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(keyword: String) = repository.getPagingMovieByKeyword(keyword)
}