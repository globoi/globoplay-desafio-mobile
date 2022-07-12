package com.nroncari.movieplay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MovieDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    var backdropPath: String,
    var average: Float = 0.0.toFloat(),
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1234
    }
}