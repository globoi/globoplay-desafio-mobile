package com.example.desafioglobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.desafioglobo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarInclude.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContent) as NavHostFragment
        navController = navHostFragment.navController

        checkFragment()
        bottomNavigationListener()

    }

    private fun checkFragment() {
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.splashFragment) {
                binding.toolbarInclude.toolbar.visibility = View.GONE
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.toolbarInclude.toolbar.visibility = View.GONE
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }

    private fun bottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.minhaLista -> {
                    navController.navigate(R.id.myListFragment)
                }
            }
            true
        }
    }

}