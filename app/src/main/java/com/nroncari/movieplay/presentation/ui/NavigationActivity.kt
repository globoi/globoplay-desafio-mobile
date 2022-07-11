package com.nroncari.movieplay.presentation.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.nroncari.movieplay.R
import com.nroncari.movieplay.databinding.FrameNavigationBinding
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private val componentsViewModel: StateAppComponentsViewModel by viewModel()
    private val binding by lazy { FrameNavigationBinding.inflate(layoutInflater) }
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.frame_navigation) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }

        componentsViewModel.components.observe(this) { havComponent ->
            if (havComponent.bottomNavigation) {
                binding.bottomNav.visibility = VISIBLE
            } else {
                binding.bottomNav.visibility = GONE
            }
        }
    }
}