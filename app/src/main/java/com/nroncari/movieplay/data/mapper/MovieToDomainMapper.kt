package com.nroncari.movieplay.data.mapper

import com.nroncari.movieplay.data.model.MovieListItemResponse
import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.utils.Mapper

class MovieToDomainMapper : Mapper<MovieListItemResponse, MovieListItemDomain> {

    override fun map(source: MovieListItemResponse): MovieListItemDomain {
        requireNotNull(source.originalTitle)
        requireNotNull(source.title)
        requireNotNull(source.posterPath)

        return MovieListItemDomain(
            originalTitle = source.originalTitle,
            title = source.title,
            posterPath = source.posterPath
        )
    }
}