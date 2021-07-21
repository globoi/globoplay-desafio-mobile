package com.example.globechallenge.data.response

import com.google.gson.annotations.SerializedName

data class MovieCreditResponse(val cast: List<Cast>)

data class Cast(
    @SerializedName("original_name")
    val originalName: String
)