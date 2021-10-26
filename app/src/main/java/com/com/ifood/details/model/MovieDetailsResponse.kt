package com.com.ifood.details.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailsResponse(
    @SerializedName("genres")
    @Expose(serialize = false)
    var genres: List<MovieDetails>,

    @SerializedName("popularity")
    @Expose(serialize = false)
    val popularity: Float,

    @SerializedName("vote_average")
    @Expose(serialize = false)
    val voteAverage: Float,

    @SerializedName("vote_count")
    @Expose(serialize = false)
    val voteCount: Int,

    @SerializedName("production_countries")
    @Expose(serialize = false)
    val productionCountries: List<MovieDetails>?
) : Parcelable