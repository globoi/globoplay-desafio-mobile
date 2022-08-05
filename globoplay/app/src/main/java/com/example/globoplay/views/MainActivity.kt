package com.example.globoplay.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.globoplay.R
import com.example.globoplay.databinding.ActivityMainBinding
import com.example.globoplay.databinding.FragmentHomeBinding
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.viewmodel.SplashScreenViewModel
import com.example.globoplay.views.inicio.InicioFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    private val navController by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)!!
            .findNavController()
    }
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isLoading.value
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.navView) { setupWithNavController(navController) }
    }
}