package com.maslima.globo_play_recrutamento.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.utils.Screen


@Composable
fun SplashScreen(navController: NavController) {
    ScreenContent()
    Thread.sleep(5000)
    navController.popBackStack()
    navController.navigate(Screen.Home.route)
}

@Preview(showBackground = true)
@Composable
fun ScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.splash),
            stringResource(id = R.string.splash_screen_content_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}