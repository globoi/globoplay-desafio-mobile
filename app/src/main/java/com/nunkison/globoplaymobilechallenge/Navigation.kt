package com.nunkison.globoplaymobilechallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nunkison.globoplaymobilechallenge.ui.Screen
import com.nunkison.globoplaymobilechallenge.ui.favorites.FavoritesScreen
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.MovieDetailScreen
import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesScreen
import com.nunkison.globoplaymobilechallenge.ui.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Movies.route)
            })
        }
        composable(Screen.Movies.route) {
            MoviesScreen(whenRequestingMovieDetails = {
                navController.navigate(Screen.MovieDetail.route)
            })
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(Screen.MovieDetail.route) {
            MovieDetailScreen({})
        }
    }
}