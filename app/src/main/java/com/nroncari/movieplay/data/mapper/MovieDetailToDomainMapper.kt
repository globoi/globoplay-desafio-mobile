package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieDetailResponse
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.utils.Mapper

class MovieDetailToDomainMapper : Mapper<MovieDetailResponse, MovieDetailDomain> {

    override fun map(source: MovieDetailResponse): MovieDetailDomain {
        requireNotNull(source.id)
        requireNotNull(source.homepage)
        requireNotNull(source.imdbId)
        requireNotNull(source.originalTitle)
        requireNotNull(source.overview)
        requireNotNull(source.posterPath)
        requireNotNull(source.releaseDate)
        requireNotNull(source.status)
        requireNotNull(source.tagline)
        requireNotNull(source.title)

        return MovieDetailDomain(
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