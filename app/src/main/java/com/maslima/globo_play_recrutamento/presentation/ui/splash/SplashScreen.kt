package com.maslima.globo_play_recrutamento.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavController
import com.maslima.globo_play_recrutamento.R

@Composable
fun SplashScreen(navController: NavController) {
    ScreenContent()
    Thread.sleep(5000)
}

@Composable
fun ScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = imageResource(id = R.drawable.splash),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}