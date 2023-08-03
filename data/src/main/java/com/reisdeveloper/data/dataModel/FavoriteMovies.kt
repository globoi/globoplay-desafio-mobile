package com.reisdeveloper.data.dataModel

import com.google.gson.annotations.SerializedName

data class FavoriteMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<FavoriteMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)