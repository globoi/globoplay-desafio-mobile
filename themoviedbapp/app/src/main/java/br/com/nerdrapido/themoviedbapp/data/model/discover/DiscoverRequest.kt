package br.com.nerdrapido.themoviedbapp.data.model.discover

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 *
 * https://developers.themoviedb.org/3/discover/movie-discover
 */
data class DiscoverRequest(
    val sortBy: String?,
    val page: Int?,
    val withGenres: String = "",
    val language: String? = "pt-BR",
    val region: String? = "BR"
)