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

@Composable
fun BottomBar(
    selectedItem: MutableState<String>,
    result: MutableState<String>
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
                    },
                    alwaysShowLabels = true
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite) },
                    selected = selectedItem.value == "favorite",
                    onClick = {
                        result.value = "favorite icon clicked"
                        selectedItem.value = "Minha Lista"
                    },
                    alwaysShowLabels = true
                )
            }
        }
    )
}