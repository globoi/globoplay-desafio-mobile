package com.nunkison.globoplaymobilechallenge.ui

import androidx.annotation.StringRes
import com.nunkison.globoplaymobilechallenge.R

sealed class Screen(
    val route: String,
    @StringRes val titleResId: Int
) {
    object Splash : Screen("splash", R.string.splash_screen_title)
    object MovieDetail : Screen("movie/999", R.string.movie_detail_screen_title)
    object Movies : Screen("movies", R.string.movies_screen_title)
    object Favorites : Screen("favorites", R.string.favorites_screen_title)
}