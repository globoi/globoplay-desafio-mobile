package br.com.favorites.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AddOrRemoveFavoriteDto(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)