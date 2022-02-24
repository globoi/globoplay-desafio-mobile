package com.globo.moviesapp.model.movieUpdateFavorite

import java.io.Serializable

data class MovieUpdateFavorite(
    var media_type: String,
    var media_id: Int,
    var favorite: Boolean
): Serializable