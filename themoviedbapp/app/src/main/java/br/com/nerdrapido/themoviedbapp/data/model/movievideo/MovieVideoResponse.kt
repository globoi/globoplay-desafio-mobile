package br.com.nerdrapido.themoviedbapp.data.model.movievideo

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieVideoObject
import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
data class MovieVideoResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<MovieVideoObject>
)