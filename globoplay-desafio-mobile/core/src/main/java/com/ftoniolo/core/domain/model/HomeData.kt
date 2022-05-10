package com.ftoniolo.core.domain.model

data class HomeData(
    val popularFilms: List<Film>,
    val adventureFilms: List<Film>,
    val animeFilms: List<Film>,
    val crimeFilms: List<Film>,
    val documentaryFilm: List<Film>,
    val horrorFilms: List<Film>,
    val romanceFilms: List<Film>,
    val warFilms: List<Film>,
)
