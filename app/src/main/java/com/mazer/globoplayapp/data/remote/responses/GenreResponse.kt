package com.mazer.globoplayapp.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.mazer.globoplayapp.domain.entities.Genre

data class GenreResponse(
    @SerializedName("genres")
    val results: List<Genre>
)