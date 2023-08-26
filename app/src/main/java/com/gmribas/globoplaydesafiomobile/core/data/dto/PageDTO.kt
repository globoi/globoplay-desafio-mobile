package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.google.gson.annotations.SerializedName

data class PageDTO<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
    )