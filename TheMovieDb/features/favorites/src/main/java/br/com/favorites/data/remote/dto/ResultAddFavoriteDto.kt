package br.com.favorites.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ResultAddFavoriteDto(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)