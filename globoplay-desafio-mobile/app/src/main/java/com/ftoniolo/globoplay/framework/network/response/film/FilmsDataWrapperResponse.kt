package com.ftoniolo.globoplay.framework.network.response.film

import com.google.gson.annotations.SerializedName

data class FilmsDataWrapperResponse<T>(
    @SerializedName("page")
    val page: Long,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)