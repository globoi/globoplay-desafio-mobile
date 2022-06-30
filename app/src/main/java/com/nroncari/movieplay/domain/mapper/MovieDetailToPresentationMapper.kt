package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieDetailToPresentationMapper : Mapper<MovieDetailDomain, MovieDetailPresentation> {

    override fun map(source: MovieDetailDomain): MovieDetailPresentation {

        return MovieDetailPresentation(
            id = source.id,
            originalTitle = source.originalTitle,
            overview = source.overview,
            posterPath = source.posterPath,
            releaseDate = source.releaseDate,
            title = source.title,
            backdropPath = source.backdropPath
        )
    }
}