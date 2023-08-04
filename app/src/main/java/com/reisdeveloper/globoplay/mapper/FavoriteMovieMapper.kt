package com.reisdeveloper.globoplay.mapper

import com.reisdeveloper.data.dataModel.Movie
import com.reisdeveloper.globoplay.ui.uiModel.FavoriteMovieUiModel

fun Movie.toUiModel() = FavoriteMovieUiModel(
    adult,
    backdropPath,
    genreIds,
    id,
    originalLanguage,
    originalTitle,
    overview,
    popularity,
    posterPath,
    releaseDate,
    title,
    video,
    voteAverage,
    voteCount
)