package me.davidpcosta.tmdb.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie (
    @SerializedName("id") var id: Long,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: Date,
    @SerializedName("genere_ids") var genreIds: IntArray,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String
)