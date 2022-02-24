package com.globo.moviesapp.model.cast

import java.io.Serializable

data class Cast(
    var name: String,
    var original_name: String,
    var order: Int
): Serializable