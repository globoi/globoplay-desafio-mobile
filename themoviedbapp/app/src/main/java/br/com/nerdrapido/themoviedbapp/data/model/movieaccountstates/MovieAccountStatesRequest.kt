package br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 *
 * https://developers.themoviedb.org/3/movies/get-movie-account-states
 */
data class MovieAccountStatesRequest(
    val movieId: Int,
    val sessionId: String?
)