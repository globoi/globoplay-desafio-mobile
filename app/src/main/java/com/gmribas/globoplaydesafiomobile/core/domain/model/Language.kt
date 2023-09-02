package com.gmribas.globoplaydesafiomobile.core.domain.model

data class Language (
    val englishName: String,
    val iso639_1: String,
    val name: String
) {

    fun toDomain(): Language = Language(englishName, iso639_1, name)
}