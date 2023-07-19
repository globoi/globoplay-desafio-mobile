package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName

data class TVList(
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val adult: Boolean
)
