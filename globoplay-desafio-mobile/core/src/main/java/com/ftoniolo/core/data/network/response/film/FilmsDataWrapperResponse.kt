package com.ftoniolo.core.data.network.response.film

import com.google.gson.annotations.SerializedName

data class FilmsDataWrapperResponse(
    val page: Long,
    val results: List<FilmResponse>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_results")
    val totalResults: Long
)