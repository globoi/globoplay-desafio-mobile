package com.nroncari.movieplay.domain.mapper

import com.nroncari.movieplay.domain.model.MovieListItemDomain
import com.nroncari.movieplay.presentation.model.MovieListItemPresentation
import com.nroncari.movieplay.utils.Mapper

class MovieToPresentationMapper : Mapper<MovieListItemDomain, MovieListItemPresentation> {

    override fun map(source: MovieListItemDomain): MovieListItemPresentation {
        return MovieListItemPresentation(
            id = source.id,
            originalTitle = source.originalTitle,
            title = source.title,
            posterPath = source.posterPath
        )
    }
}