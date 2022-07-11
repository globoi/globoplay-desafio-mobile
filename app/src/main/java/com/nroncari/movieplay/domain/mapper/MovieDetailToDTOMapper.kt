package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.data.model.MovieDTO
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieDetailToDTOMapper : Mapper<MovieDetailPresentation, MovieDTO> {

    override fun map(source: MovieDetailPresentation): MovieDTO {
        return MovieDTO(
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