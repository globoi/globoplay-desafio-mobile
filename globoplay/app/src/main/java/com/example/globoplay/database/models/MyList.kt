package com.example.globoplay.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyList(
    @PrimaryKey var mediaName: String,
    @ColumnInfo(name = "desc_media") var description:String?,
    @ColumnInfo(name = "poster_image")  var posterImage:String?,
    @ColumnInfo(name = "release_date") var releaseDate:String?,
    @ColumnInfo(name = "vote_average") var voteAverage:String?
)
