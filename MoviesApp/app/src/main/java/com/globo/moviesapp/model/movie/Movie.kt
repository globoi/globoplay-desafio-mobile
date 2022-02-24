package com.globo.moviesapp.model.movie

import com.globo.moviesapp.model.genre.Genre
import com.globo.moviesapp.model.movieAggregateCredits.MovieAggregateCredits
import com.globo.moviesapp.model.productionCountry.ProductionCountry
import java.io.Serializable

data class Movie(
    var id: Int?,
    var name: String?,
    var original_name: String?,
    var type: String?,
    var genre_ids: List<Int>?,
    var genres: List<Genre>?,
    var poster_path: String?,
    var number_of_episodes: Int?,
    var first_air_date: String?,
    var production_countries: List<ProductionCountry>?,
    var overview: String = "",
    var aggregateCredits: MovieAggregateCredits? = null,
    var isFavorite: Boolean = false,
    var keyYoutube: String? = null
): Serializable