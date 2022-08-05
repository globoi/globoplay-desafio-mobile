package com.example.globoplay.domain

data class PopularTVSeries(
    val id: Long,
    val name: String,
    val overview: String,
    val releaseDate:String,
    val posterPath:String,
    val voteAverage:Double
)
