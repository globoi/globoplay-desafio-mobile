package com.ftoniolo.globoplay.framework.network.response.trailer

import com.google.gson.annotations.SerializedName

data class TrailerDataWrapperResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("results")
    val results: List<TrailerResponse>
)
