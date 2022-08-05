package com.example.globoplay.data.remote.dto

import com.example.globoplay.domain.PopularMovie
import com.google.gson.annotations.SerializedName

data class PopularMovieDto(
    val id:Long,
    val title:String,
    val overview:String,
    @SerializedName("release_date")
    val releaseDate:String,
    @SerializedName("vote_average")
    val voteAverage:Double,
    @SerializedName("poster_path")
    val posterPath:String

){
    fun toPopularMovie(): PopularMovie{
        return PopularMovie(
            id = id,
            title = title,
            description = overview,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            posterPath = posterPath
        )
    }

}
