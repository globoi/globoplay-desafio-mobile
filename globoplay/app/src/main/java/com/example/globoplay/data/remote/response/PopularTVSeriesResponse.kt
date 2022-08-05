package com.example.globoplay.data.remote.response
import com.example.globoplay.data.remote.dto.PopularTVSeriesDto
import com.google.gson.annotations.SerializedName

data class PopularTVSeriesResponse(
    @SerializedName("results")
    val popularTVSeries: List<PopularTVSeriesDto>
)
