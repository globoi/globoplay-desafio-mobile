package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import com.google.gson.annotations.SerializedName
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieCategory.*

enum class MovieCategory(val value: String) {
    @SerializedName("28")
    ACTION("Ação"),

    @SerializedName("12")
    ADVENTURE("Aventura"),

    @SerializedName("16")
    ANIMATION("Animação"),

    @SerializedName("35")
    COMEDY("Comédia"),

    @SerializedName("80")
    CRIME("Crime"),

    @SerializedName("99")
    DOCUMENTARY("Documentário"),

    @SerializedName("18")
    DRAMA("Drama"),

    @SerializedName("10751")
    FAMILY("Família"),

    @SerializedName("14")
    FANTASY("Fantasia"),

    @SerializedName("36")
    HISTORY("História"),

    @SerializedName("27")
    HORROR("Horror"),

    @SerializedName("10402")
    MUSIC("Musical"),

    @SerializedName("9648")
    MYSTERY("Mistério"),

    @SerializedName("10749")
    ROMANCE("Romance"),

    @SerializedName("878")
    SCI_FI("Sci fi"),

    @SerializedName("10770")
    TV_MOVIE("Filmes de TV"),

    @SerializedName("53")
    THRILLER("Thriller"),

    @SerializedName("10752")
    WAR("Guerra"),

    @SerializedName("37")
    WESTERN("Velho Oeste"),
}

fun getAllMoviesCategories(): List<MovieCategory> {
    return listOf(
        ACTION,
        ADVENTURE,
        ANIMATION,
        COMEDY,
        CRIME,
        DOCUMENTARY,
        DRAMA,
        FAMILY,
        FANTASY,
        HISTORY,
        HORROR,
        MUSIC,
        MYSTERY,
        ROMANCE,
        SCI_FI,
        TV_MOVIE,
        THRILLER,
        WAR,
        WESTERN
    )
}

fun getIdByCategory(movieCategory: MovieCategory?): Int {
    return when (movieCategory) {
        ACTION -> 28
        ADVENTURE -> 12
        ANIMATION -> 16
        COMEDY -> 35
        CRIME -> 80
        DOCUMENTARY -> 99
        DRAMA -> 18
        FAMILY -> 10751
        FANTASY -> 14
        HISTORY -> 36
        HORROR -> 27
        MUSIC -> 10402
        MYSTERY -> 9648
        ROMANCE -> 10749
        SCI_FI -> 878
        TV_MOVIE -> 10770
        THRILLER -> 53
        WAR -> 10752
        WESTERN -> 37
        else -> 0
    }
}

fun getMovieCategory(value: String): MovieCategory? {
    val map = MovieCategory.values().associateBy(MovieCategory::value)
    return map[value]
}