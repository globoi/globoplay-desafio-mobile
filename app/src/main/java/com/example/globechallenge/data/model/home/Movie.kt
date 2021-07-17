package com.example.globechallenge.data.model.home

data class Movie(
    val id: String,
    val name: String,
    val genre: List<Int>,
    val image: String
)