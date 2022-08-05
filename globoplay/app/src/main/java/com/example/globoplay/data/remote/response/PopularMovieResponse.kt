package com.example.globoplay.data.remote.response

import com.example.globoplay.data.remote.dto.PopularMovieDto
import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("results")
    val popularMovie: List<PopularMovieDto>
)
