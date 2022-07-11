package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieDataVideoDomain
import com.nroncari.movieplay.presentation.model.MovieDataVideoPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieDataVideoToPresentationMapper: Mapper<MovieDataVideoDomain, MovieDataVideoPresentation> {

    override fun map(source: MovieDataVideoDomain): MovieDataVideoPresentation {
        return MovieDataVideoPresentation(
            source.path,
            source.site
        )
    }
}