package br.com.favorites.domain.model

import com.google.gson.annotations.SerializedName

data class ResultAddFavorite (
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)