package com.nunkison.globoplaymobilechallenge.project.api

class MovieResponse(
    val id: String,
    val genres: List<Genre>,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val production_companies: List<ProductionCompany>,
    val revenue: Int,
    val runtime: Int,
    val vote_average: Double,
    val budget: Int
)