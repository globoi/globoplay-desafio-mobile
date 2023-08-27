package com.gmribas.globoplaydesafiomobile.core.presentation.navigation

sealed class Screens(val route: String) {

    object Home : Screens("home")
}
