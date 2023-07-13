package com.nunkison.globoplaymobilechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nunkison.globoplaymobilechallenge.ui.Screen
import com.nunkison.globoplaymobilechallenge.ui.favorites.FavoritesScreen
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.MovieDetailScreen
import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesScreen
import com.nunkison.globoplaymobilechallenge.ui.splash.SplashScreen
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GloboplayMobileChallengeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable(Screen.Splash.route) { SplashScreen(navController) }
                    composable(Screen.Movies.route) { MoviesScreen(navController) }
                    composable(Screen.Favorites.route) { FavoritesScreen(navController) }
                    composable(Screen.MovieDetail.route) { MovieDetailScreen(navController) }
                }
            }
        }
    }
}