package com.com.globo.repository.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Movie(
    @SerializedName("id")
    @Expose(serialize = false)
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,

    @SerializedName("title")
    @Expose(serialize = false)
    @ColumnInfo(name = "title")
    var title: String,

    @SerializedName("overview")
    @Expose(serialize = false)
    @ColumnInfo(name = "description")
    var description: String,

    @SerializedName("poster_path")
    @Expose(serialize = false)
    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @SerializedName("capaPath")
    @Expose(serialize = false)
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?,

    @SerializedName("release_date")
    @Expose(serialize = false)
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @SerializedName("vote_average")
    @Expose(serialize = false)
    @ColumnInfo(name = "rating")
    var rating: Float?,

    @SerializedName("popularity")
    @Expose(serialize = false)
    @ColumnInfo(name = "popularity")
    var popularity: Float?,

    @SerializedName("original_title")
    @Expose(serialize = false)
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String?
) : Parcelable
    
