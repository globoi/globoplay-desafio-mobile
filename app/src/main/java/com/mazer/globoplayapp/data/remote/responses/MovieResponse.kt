package com.mazer.globoplayapp.data.remote.responses

import com.mazer.globoplayapp.domain.entities.Movie

data class MovieResponse(
    val results: List<Movie>
    )