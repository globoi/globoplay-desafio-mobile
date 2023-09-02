package com.gmribas.globoplaydesafiomobile.core.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmribas.globoplaydesafiomobile.core.domain.model.Language
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity(tableName = "media_details_entity")
data class MediaDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val isTvShow: Boolean,
    val poster: String?,
    val backdrop: String?,
    val adult: Boolean,
    val originalTitle: String,
    val overview: String
) : Parcelable {

    fun toDomain(): MediaDetails {
        return MediaDetails(
            id = id,
            title = title,
            isTvShow = isTvShow,
            poster = poster,
            backdrop = backdrop,
            adult = adult,
            originalTitle = originalTitle,
            overview = overview,
            spokenLanguages = emptyList(),
            videoList = null
        )
    }
}