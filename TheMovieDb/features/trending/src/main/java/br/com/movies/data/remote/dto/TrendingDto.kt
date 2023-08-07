package br.com.movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TrendingDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TrendingMoviesDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)