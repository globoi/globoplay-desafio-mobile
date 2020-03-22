package br.com.nerdrapido.themoviedbapp.constant

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * This enum stores URL information
 */
enum class URL(val url: String) {
    TMDB_API("https://api.themoviedb.org/3/"),
    TMDB_BACKDROP("https://image.tmdb.org/t/p/original"),
    TMDB_POSTER("https://image.tmdb.org/t/p/w500"),
    TMDB_AUTHENTICATE("https://www.themoviedb.org/authenticate/")
}