package com.gmribas.globoplaydesafiomobile.core.domain.model

interface DetailsInterface : PosterItemInterface {
    val adult: Boolean
    val originalTitle: String
    val overview: String
    val spokenLanguages: List<Language>
}