package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieDetailResponse
import com.nroncari.movieplay.domain.model.MovieDetailDomain
import com.nroncari.movieplay.utils.Mapper

class MovieDetailToDomainMapper : Mapper<MovieDetailResponse, MovieDetailDomain> {

    override fun map(source: MovieDetailResponse): MovieDetailDomain {
        requireNotNull(source.id)
        requireNotNull(source.originalTitle)
        requireNotNull(source.overview)
        requireNotNull(source.posterPath)
        requireNotNull(source.releaseDate)
        requireNotNull(source.title)
        requireNotNull(source.backdropPath)

        return MovieDetailDomain(
            id = source.id,
            originalTitle = source.originalTitle,
            overview = source.overview,
            posterPath = source.posterPath,
            releaseDate = source.releaseDate,
            title = source.title,
            backdropPath = source.backdropPath,
            average = source.average
        )
    }
}