package com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.presentation.navigation.Screens

sealed class BottomNavItem(
    val title: Int,
    val icon: ImageVector,
    val screenRoute: Screens
) {
    object Home :
        BottomNavItem(
            R.string.home_bottom_bar_home_label,
            Icons.Filled.Home,
            Screens.Home
        )

    object MyList :
        BottomNavItem(
            R.string.home_bottom_bar_my_list_label,
            Icons.Filled.Star,
            Screens.MyList
        )
}