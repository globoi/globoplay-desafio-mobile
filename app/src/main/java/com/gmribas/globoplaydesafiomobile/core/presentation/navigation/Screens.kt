package com.gmribas.globoplaydesafiomobile.core.presentation.navigation

sealed class Screens(val route: String) {

    object Home : Screens("home")

    object Details : Screens("details")

    object MyList : Screens("my_list")
}
