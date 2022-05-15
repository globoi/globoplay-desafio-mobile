package com.simonassi.globoplay.data.tv

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a movie from TMDB.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below.
 */
data class Tv(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("original_name") val originalTitle: String,
    @SerializedName("genre_ids") val genders: List<Int>,
    @SerializedName("poster_path") var cover: String,
    @SerializedName("backdrop_path") var backdropCover: String
)