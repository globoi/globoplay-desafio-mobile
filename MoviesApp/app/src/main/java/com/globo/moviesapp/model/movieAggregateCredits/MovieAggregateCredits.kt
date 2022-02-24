package com.globo.moviesapp.model.movieAggregateCredits

import com.globo.moviesapp.model.cast.Cast
import com.globo.moviesapp.model.crew.Crew
import java.io.Serializable

data class MovieAggregateCredits(
    var cast: List<Cast>,
    var crew: List<Crew>
): Serializable