package com.nroncari.movieplay.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nroncari.movieplay.R

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.frame_navigation) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_navigation)

        NavigationUI.setupWithNavController(
            this.findViewById<BottomNavigationView>(R.id.bottom_nav),
            navController
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}