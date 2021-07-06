package com.maslima.globo_play_recrutamento.network.model

import com.google.gson.annotations.SerializedName

data class ImageConfigDto (
    @SerializedName("base_url") val base_url : String,
    @SerializedName("secure_base_url") val secure_base_url : String,
    @SerializedName("backdrop_sizes") val backdrop_sizes : List<String>,
    @SerializedName("logo_sizes") val logo_sizes : List<String>,
    @SerializedName("poster_sizes") val poster_sizes : List<String>,
    @SerializedName("profile_sizes") val profile_sizes : List<String>,
    @SerializedName("still_sizes") val still_sizes : List<String>
)