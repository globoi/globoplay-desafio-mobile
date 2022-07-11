package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.data.model.MovieDTO
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieDTOToPresentationMapper : Mapper<MovieDTO, MovieDetailPresentation> {

    override fun map(source: MovieDTO): MovieDetailPresentation {
        return MovieDetailPresentation(
            source.id,
            source.originalTitle,
            source.overview,
            source.posterPath,
            source.releaseDate,
            source.title,
            source.backdropPath,
            source.average
        )
    }
}