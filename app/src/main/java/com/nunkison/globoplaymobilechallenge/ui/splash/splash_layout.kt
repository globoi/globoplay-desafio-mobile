package com.nunkison.globoplaymobilechallenge.ui.splash

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

@Composable
fun SplashLayout(
    @DrawableRes logo: Int,
    backgroundColor: Color
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(40.dp)
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    GloboplayMobileChallengeTheme {
        SplashLayout(
            logo = R.drawable.logo_globoplay_white,
            backgroundColor = Color.Black
        )
    }
}