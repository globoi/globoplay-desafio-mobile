package br.com.common.data.dto


import com.google.gson.annotations.SerializedName

data class ResultMoviesDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MoviesDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)