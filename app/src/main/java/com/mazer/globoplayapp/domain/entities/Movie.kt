package com.mazer.globoplayapp.domain.entities

import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Movie(
    @PrimaryKey
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    var title: String,
    @Ignore
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    @SerializedName("original_language")
    var originalLanguage: String,
    var genre: String,
    var videoUrl: String
) : Parcelable {

    constructor() : this(0, "", "", "", "", "", emptyList(), "", "", "")




    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createIntArray()?.toList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeIntArray(genreIds.toIntArray())
        parcel.writeString(originalLanguage)
        parcel.writeString(genre)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
