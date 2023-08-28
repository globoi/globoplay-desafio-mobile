package com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun BottomNavigation(selectedScreen: BottomNavItem, onClick: (screen: BottomNavItem) -> Unit) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyList
    )

    NavigationBar(
        containerColor = Color.Black
    ) {
        items.forEach { item ->
            AddItem(
                screen = item,
                selectedScreen = selectedScreen,
                onClick = onClick
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    selectedScreen: BottomNavItem,
    onClick: (screen: BottomNavItem) -> Unit
) {
    NavigationBarItem(
        label = {
            Text(text = stringResource(id = screen.title))
        },
        icon = {
            Icon(
                screen.icon,
                contentDescription = stringResource(id = screen.title)
            )
        },
        selected = screen == selectedScreen,
        alwaysShowLabel = true,
        onClick = { onClick(screen) },
        colors = NavigationBarItemDefaults.colors()
    )
}