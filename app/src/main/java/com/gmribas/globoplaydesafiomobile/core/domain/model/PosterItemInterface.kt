package com.gmribas.globoplaydesafiomobile.core.domain.model

interface PosterItemInterface {

    val id: Int
    val title: String
    val isTvShow: Boolean
    val poster: String?
    val backdrop: String?
}