package com.nunkison.globoplaymobilechallenge.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.ui.Screen
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screen.Movies.route)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SplashLayout(logo = R.drawable.globoplay_logo, backgroundColor = MaterialTheme.colorScheme.background)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    GloboplayMobileChallengeTheme {
        SplashScreen(rememberNavController())
    }
}