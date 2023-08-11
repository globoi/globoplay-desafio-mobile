package br.com.favorites.domain.model


data class AddOrRemoveFavorite(

    val favorite: Boolean,

    val mediaId: Int,

    val mediaType: String
)
