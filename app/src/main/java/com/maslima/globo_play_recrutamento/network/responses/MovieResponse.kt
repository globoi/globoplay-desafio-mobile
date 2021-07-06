package com.maslima.globo_play_recrutamento.network.responses

import com.google.gson.annotations.SerializedName
import com.maslima.globo_play_recrutamento.network.model.MovieDto

data class MovieResponse(
    @SerializedName("page") val page : Int,
    @SerializedName("results") val movies : List<MovieDto>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
)