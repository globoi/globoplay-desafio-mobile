package com.gmribas.globoplaydesafiomobile.core.data.database.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model.Media
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity(tableName = "media_entity")
data class MediaEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val isTvShow: Boolean,
    val poster: String?,
    val backdrop: String?
) : Parcelable {

    fun toDomain(): Media {
        return Media(id = id, title = title, isTvShow = isTvShow, poster = poster, backdrop = backdrop)
    }
}