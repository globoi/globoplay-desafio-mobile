package com.com.globo.repository.config

import com.com.globo.repository.MovieApi

private val movieApi =
    createApi<MovieApi>()

fun provideMovieApi() = movieApi