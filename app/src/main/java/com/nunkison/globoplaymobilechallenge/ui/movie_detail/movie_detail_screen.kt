package com.nunkison.globoplaymobilechallenge.ui.movie_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

@Composable
fun MovieDetailScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(
                text = "MovieDetailScreen",
            )
            Button(onClick = {
//                navController.navigate(Screen.Splash.route)
            }) {
                Text(
                    text = "Next",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    GloboplayMobileChallengeTheme {
        MovieDetailScreen()
    }
}