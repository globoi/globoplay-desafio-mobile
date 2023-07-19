package com.nunkison.globoplaymobilechallenge

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nunkison.globoplaymobilechallenge.ui.favorites.FavoritesScreen
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.MovieDetailScreen
import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesScreen
import com.nunkison.globoplaymobilechallenge.ui.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object MovieDetail : Screen("movie/{id}") {
        fun with(movieId: String) = route.replace("{id}", movieId)
    }

    object Movies : Screen("movies")
    object Favorites : Screen("favorites")
}

@Composable
fun Navigation(context: Context, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Movies.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Screen.Movies.route) {
            MoviesScreen(
                whenRequestingMovieDetails = {
                    navController.navigate(
                        Screen.MovieDetail.with(it)
                    )
                }
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(Screen.MovieDetail.route) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let { id ->
                MovieDetailScreen(
                    movieId = id,
                    whenRequestingMovieDetails = {
                        navController.navigate(
                            Screen.MovieDetail.with(it)
                        )
                    }, whenRequestBack = {
                        navController.popBackStack()
                    }, {
                        val videoId = "Fee5vbFLYM4"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$it"))
                        intent.putExtra("VIDEO_ID", videoId)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}