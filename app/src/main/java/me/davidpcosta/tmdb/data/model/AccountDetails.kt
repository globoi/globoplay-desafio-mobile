package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class AccountDetails (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("avatar") val avatar: Avatar
)

data class Avatar (
    @SerializedName("gravatar") val gravatar: Gravatar
)

data class Gravatar (
    @SerializedName("hash") val hash: String
)