package com.maslima.globo_play_recrutamento.utils

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SplashScreen : Screen("splash")
    object MyList : Screen("myList")
    object Details : Screen("details")
}