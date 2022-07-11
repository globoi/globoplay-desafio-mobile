package com.nroncari.movieplay.domain.usecase

import com.nroncari.movieplay.domain.mapper.MovieDataVideoToPresentationMapper
import com.nroncari.movieplay.domain.repository.MovieRepository
import com.nroncari.movieplay.presentation.model.MovieDataVideoPresentation

class GetMovieDataVideoUseCase(
    private val repository: MovieRepository
) {

    val mapper = MovieDataVideoToPresentationMapper()

    suspend operator fun invoke(movieId: Long): MovieDataVideoPresentation? {
        val youtubeDataVideo = repository.getMovieDataVideo(movieId).find {
            it.site.equals("YouTube", true)
        }
        return youtubeDataVideo?.let { mapper.map(it) }
    }
}