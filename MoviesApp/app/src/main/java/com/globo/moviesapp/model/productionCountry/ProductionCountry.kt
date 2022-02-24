package com.globo.moviesapp.model.productionCountry

import java.io.Serializable

data class ProductionCountry(
    var iso_3166_1: String? = null,
    var name: String? = null
): Serializable