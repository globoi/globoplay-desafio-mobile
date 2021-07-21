package com.example.globechallenge.data.response

data class MovieVideosResponse(
    val id: Int,
    val results: List<Results>?
)

data class Results (
    val key: String
)