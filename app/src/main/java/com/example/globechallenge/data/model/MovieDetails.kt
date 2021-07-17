package com.example.globechallenge.data.model

import com.example.globechallenge.data.response.GenresItemDetail

data class MovieDetails(
    val title: String,
    val genres: List<GenresItemDetail>,
    val runtime: Int,
    val overview: String,
    val releaseDate: String,
    val languages: String,
    val postPath: String
)