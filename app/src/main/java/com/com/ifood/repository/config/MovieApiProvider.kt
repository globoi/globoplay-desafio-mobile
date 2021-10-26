package com.com.ifood.repository.config

import com.com.ifood.repository.MovieApi

private val movieApi =
    createApi<MovieApi>()

fun provideMovieApi() = movieApi