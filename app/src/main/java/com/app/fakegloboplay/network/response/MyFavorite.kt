package com.app.fakegloboplay.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MyFavorite(
    @SerializedName("media_type")
    @Expose
    val mediaType: String,
    @SerializedName("media_id")
    @Expose
    val mediaId: Int,
    @SerializedName("favorite")
    @Expose
    val favorite: Boolean,
)