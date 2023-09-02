package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.google.gson.annotations.SerializedName

data class LanguageDTO (
    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    val name: String
)