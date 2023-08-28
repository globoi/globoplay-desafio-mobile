package com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.gmribas.globoplaydesafiomobile.R

sealed class BottomNavItem(
    val title: Int,
    val icon: ImageVector
) {
    object Home :
        BottomNavItem(
            R.string.home_bottom_bar_home_label,
            Icons.Filled.Home
        )

    object MyList :
        BottomNavItem(
            R.string.home_bottom_bar_my_list_label,
            Icons.Filled.Star
        )
}