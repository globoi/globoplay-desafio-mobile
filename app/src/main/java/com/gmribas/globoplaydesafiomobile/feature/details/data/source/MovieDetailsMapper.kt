package com.gmribas.globoplaydesafiomobile.feature.details.data.source

import com.gmribas.globoplaydesafiomobile.core.data.dto.MovieDetailsDTO
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails

fun MovieDetailsDTO.toDomain(): MovieDetails {
    return MovieDetails(
        adult,
        backdropPath,
        homepage,
        id,
        imdbID,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        title,
        video
    )
}