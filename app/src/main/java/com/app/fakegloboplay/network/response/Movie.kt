package com.app.fakegloboplay.network.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("original_name")
    @Expose
    val originalName: String,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("vote_verage")
    @Expose
    val voteAverage: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Long,
    @SerializedName("media_type")
    @Expose
    var mediaType: String

) : Parcelable {

    override fun hashCode(): Int {
        return id.hashCode()
    }
}