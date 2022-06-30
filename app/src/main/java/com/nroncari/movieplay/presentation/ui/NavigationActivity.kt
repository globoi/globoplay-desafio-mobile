package com.nroncari.movieplay.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nroncari.movieplay.R
import com.nroncari.movieplay.databinding.FrameNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.frame_navigation)
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