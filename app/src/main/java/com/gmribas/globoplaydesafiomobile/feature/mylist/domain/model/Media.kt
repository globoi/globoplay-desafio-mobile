package com.gmribas.globoplaydesafiomobile.feature.mylist.domain.model

import com.gmribas.globoplaydesafiomobile.core.data.database.entity.MediaEntity
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface

class Media(
    override val id: Int,
    override val title: String,
    override val isTvShow: Boolean,
    override val poster: String?,
    override val backdrop: String?
) : PosterItemInterface {

    fun toMediaEntity(): MediaEntity {
        return MediaEntity(id, title, isTvShow, poster, backdrop)
    }
}