package com.maslima.globo_play_recrutamento.domain.model

data class ImageConfig(
    val baseUrl : String,
    val secureBaseUrl : String,
    val backdropSizes : List<String>,
    val logoSizes : List<String>,
    val posterSizes : List<String>,
    val profileSizes : List<String>,
    val stillSizes : List<String>
)
