package com.example.globechallenge.data.model.details

import com.example.globechallenge.data.response.GenresItemDetail

data class MovieDetails(
    val title: String,
    val genres: List<GenresItemDetail>,
    val runtime: Int,
    val overview: String,
    val releaseDate: String,
    val countryName: String,
    val postPath: String
)