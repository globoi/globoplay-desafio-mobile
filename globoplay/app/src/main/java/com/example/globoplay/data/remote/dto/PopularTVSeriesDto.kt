package com.example.globoplay.data.remote.dto

import com.example.globoplay.data.remote.SeriesAPI
import com.example.globoplay.domain.PopularMovie
import com.example.globoplay.domain.PopularTVSeries
import com.google.gson.annotations.SerializedName

data class PopularTVSeriesDto(
    val id: Long,
    val name: String,
    val overview: String,
    @SerializedName("first_air_date")
    val releaseDate:String,
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("vote_average")
    val voteAverage:Double
){
    fun toPopularSeries(): PopularTVSeries{
        return PopularTVSeries(
            id = id,
            name = name,
            overview = overview,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            posterPath = posterPath
        )
    }

}
