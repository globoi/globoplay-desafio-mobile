package com.example.globoplay.domain

data class PopularMovie(
    val id:Long,
    val title:String,
    val description:String,
    val releaseDate: String,
    val voteAverage:Double,
    val posterPath:String
)
