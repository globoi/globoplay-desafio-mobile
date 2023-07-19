package com.app.fakegloboplay.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results:List<Movie>,
    @SerializedName("total_pages")
    @Expose
    val totalPage: Int,
    @SerializedName("total_results")
    @Expose
    val totalResult: Int
)