package com.nunkison.globoplaymobilechallenge.ui

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object MovieDetail : Screen("movie/{id}") {
        fun with(movieId: String) = route.replace("{id}", movieId)
    }
    object Movies : Screen("movies")
    object Favorites : Screen("favorites")
}