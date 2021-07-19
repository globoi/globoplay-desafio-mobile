package com.com.globo.details.helper

import com.com.globo.details.model.MovieDetails

fun getNamesFormattedMovieDetails(movieDetails: List<MovieDetails>?): String? {
    if (movieDetails.isNullOrEmpty()) {
        return null
    }

    var names = ""
    for ((index, detail) in movieDetails.withIndex()) {
        if (index == 0) {
            names = detail.name
        } else {
            names += ", ${detail.name}"
        }
    }

    return names
}
