package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a movie from TMDB.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below.
 */
data class Tv(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("original_name") val originalTitle: String,
    @SerializedName("genre_ids") val genders: List<Int>,
    @SerializedName("poster_path") val cover: String,
    @SerializedName("backdrop_path") val backdropCover: String
)