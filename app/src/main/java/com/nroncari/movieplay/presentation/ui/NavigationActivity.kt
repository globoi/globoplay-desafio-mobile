package com.nroncari.movieplay.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.nroncari.movieplay.R

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.frame_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.frame_navigation)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}