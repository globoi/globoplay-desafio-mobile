package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieDetailToPresentationMapper : Mapper<MovieDetailDomain, MovieDetailPresentation> {

    override fun map(source: MovieDetailDomain): MovieDetailPresentation {

        return MovieDetailPresentation(
            id = source.id,
            budget = source.budget,
            homepage = source.homepage,
            imdbId = source.imdbId,
            originalTitle = source.originalTitle,
            overview = source.overview,
            popularity = source.popularity,
            posterPath = source.posterPath,
            releaseDate = source.releaseDate,
            revenue = source.revenue,
            runtime = source.runtime,
            status = source.status,
            tagline = source.tagline,
            title = source.title,
            video = source.video,
            voteAverage = source.voteAverage,
            voteCount = source.voteCount,
        )
    }
}