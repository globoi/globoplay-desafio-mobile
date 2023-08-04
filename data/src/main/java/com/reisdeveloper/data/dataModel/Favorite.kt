package com.reisdeveloper.data.dataModel

import com.google.gson.annotations.SerializedName

data class Favorite(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)