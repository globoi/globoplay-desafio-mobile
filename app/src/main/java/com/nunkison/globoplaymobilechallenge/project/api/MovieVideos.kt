package com.nunkison.globoplaymobilechallenge.project.api

data class MovieVideos(
    val results: List<Result>
){
    data class Result(
        val site: String,
        val key: String,
    )
}