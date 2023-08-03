package com.reisdeveloper.globoplay.mapper

import com.reisdeveloper.data.dataModel.FavoriteMovie
import com.reisdeveloper.globoplay.ui.uiModel.FavoriteMovieUiModel

fun FavoriteMovie.toUiModel() = FavoriteMovieUiModel(
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