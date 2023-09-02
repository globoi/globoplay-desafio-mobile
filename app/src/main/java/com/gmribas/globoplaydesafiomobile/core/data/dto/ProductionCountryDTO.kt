package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountryDTO (
    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String
)