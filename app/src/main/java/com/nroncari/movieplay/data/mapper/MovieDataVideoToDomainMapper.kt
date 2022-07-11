package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieDataVideoResponse
import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.utils.Mapper

class MovieDataVideoToDomainMapper : Mapper<MovieDataVideoResponse, MovieDataVideoDomain> {

    override fun map(source: MovieDataVideoResponse): MovieDataVideoDomain {
        requireNotNull(source.path)
        requireNotNull(source.site)

        return MovieDataVideoDomain(
            source.path,
            source.site
        )
    }
}