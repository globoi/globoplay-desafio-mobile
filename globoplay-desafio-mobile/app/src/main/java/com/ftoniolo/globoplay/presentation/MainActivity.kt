package com.ftoniolo.globoplay.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        val navView: BottomNavigationView = binding.bottomNavMain

        navView.menu.findItem(R.id.menu_disable1)
            .isEnabled = false
        navView.menu.findItem(R.id.menu_disable2)
            .isEnabled = false
        navView.menu.findItem(R.id.home)
            .isChecked = true

        navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.favoritesFragment)
        )

        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
            }
            when (destination.id) {
                R.id.homeFragment -> { binding.toolbarLogo.isVisible = true }
                R.id.favoritesFragment -> { binding.toolbarLogo.isVisible = false}
            }
        }
    }
}