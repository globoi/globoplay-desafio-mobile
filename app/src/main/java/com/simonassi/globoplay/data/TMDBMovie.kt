package com.simonassi.globoplay.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a movie from TMDB.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below.
 */
data class TMDBMovie(
    @SerializedName("original_title") val title: String
)