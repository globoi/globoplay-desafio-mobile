package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.maslima.globo_play_recrutamento.R

@Composable
fun BottomBar(
    selectedItem: MutableState<String>,
    result: MutableState<String>,
    navController: NavController
) {
    BottomAppBar(
        content = {
            BottomNavigation() {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home) },
                    selected = selectedItem.value == "home",
                    onClick = {
                        result.value = "home icon clicked"
                        selectedItem.value = "Início"
                        navController.navigate(R.id.favoriteListToMovieList)
                    },
                    alwaysShowLabels = true
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite) },
                    selected = selectedItem.value == "favorite",
                    onClick = {
                        result.value = "favorite icon clicked"
                        selectedItem.value = "Favoritos"
                        navController.navigate(R.id.movieListToFavoriteList)
                    },
                    alwaysShowLabels = true
                )
            }
        }
    )
}