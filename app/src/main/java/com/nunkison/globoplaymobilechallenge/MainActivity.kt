package com.nunkison.globoplaymobilechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            GloboplayMobileChallengeTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}